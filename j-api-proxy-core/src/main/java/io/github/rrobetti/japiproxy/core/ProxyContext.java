package io.github.rrobetti.japiproxy.core;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Shared proxy state including filters, resource name, and identity-based proxy reuse.
 */
public final class ProxyContext {
    private final List<InvocationFilter> filters;
    private final String resourceName;
    private final Object cacheMonitor = new Object();
    private final List<CacheEntry> cacheEntries = new ArrayList<>();

    /**
     * Creates a new proxy context.
     *
     * @param resourceName the logical resource name, if any
     * @param filters the invocation filters to apply in insertion order
     */
    public ProxyContext(String resourceName, List<InvocationFilter> filters) {
        this.resourceName = resourceName;
        this.filters = List.copyOf(filters == null ? List.of() : filters);
    }

    /**
     * Returns the configured resource name.
     *
     * @return the resource name, or {@code null}
     */
    public String resourceName() {
        return resourceName;
    }

    /**
     * Returns the configured filters.
     *
     * @return the immutable filter list
     */
    public List<InvocationFilter> filters() {
        return filters;
    }

    /**
     * Wraps a delegate with a JDK dynamic proxy, reusing a cached proxy when possible.
     *
     * @param delegate the delegate to wrap
     * @param iface the primary proxied interface
     * @param parent the parent proxy or object, if any
     * @param returnValueAdapter the optional return value adapter
     * @param argumentAdapter the optional argument adapter
     * @param additionalInterfaces additional interfaces to expose on the proxy
     * @param <T> the proxy type
     * @return the proxy instance or the original proxy when already wrapped
     */
    public <T> T wrap(Object delegate, Class<T> iface, Object parent,
            ReturnValueAdapter returnValueAdapter, ArgumentAdapter argumentAdapter,
            Class<?>... additionalInterfaces) {
        Objects.requireNonNull(delegate, "delegate");
        Objects.requireNonNull(iface, "iface");
        if (!iface.isInterface()) {
            throw new IllegalArgumentException("Primary type must be an interface: " + iface.getName());
        }

        Class<?>[] extraInterfaces = additionalInterfaces == null ? new Class<?>[0] : additionalInterfaces.clone();
        Object existingProxy = tryReturnExistingProxy(delegate, iface, extraInterfaces);
        if (existingProxy != null) {
            return iface.cast(existingProxy);
        }

        Object rawDelegate = delegate instanceof ProxyHandle proxyHandle ? proxyHandle.delegate() : delegate;
        // Cache lookup and proxy creation must happen as a single atomic operation under
        // cacheMonitor so concurrent callers wrapping the same delegate always observe (or
        // create) exactly one proxy instance, preserving stable proxy identity.
        synchronized (cacheMonitor) {
            for (int index = 0; index < cacheEntries.size(); index++) {
                CacheEntry entry = cacheEntries.get(index);
                if (entry.stale()) {
                    cacheEntries.remove(index--);
                    continue;
                }
                if (entry.matches(rawDelegate)) {
                    Object cachedProxy = entry.proxy();
                    if (cachedProxy != null && supportsAll(cachedProxy, iface, extraInterfaces)) {
                        return iface.cast(cachedProxy);
                    }
                }
            }

            InvocationHandler handler = new FrameworkInvocationHandler(rawDelegate, iface, parent, this,
                    returnValueAdapter, argumentAdapter);
            Set<Class<?>> interfaces = new LinkedHashSet<>();
            interfaces.add(iface);
            Collections.addAll(interfaces, extraInterfaces);
            interfaces.add(ProxyHandle.class);
            for (Class<?> candidate : interfaces) {
                if (!candidate.isInterface()) {
                    throw new IllegalArgumentException("Additional type must be an interface: " + candidate.getName());
                }
                if (candidate != ProxyHandle.class && !candidate.isInstance(rawDelegate)) {
                    throw new IllegalArgumentException("Delegate does not implement " + candidate.getName());
                }
            }
            Object proxy = Proxy.newProxyInstance(resolveClassLoader(rawDelegate, iface, interfaces),
                    interfaces.toArray(Class<?>[]::new), handler);
            cacheEntries.add(new CacheEntry(rawDelegate, proxy));
            return iface.cast(proxy);
        }
    }

    /**
     * Recursively unwraps a proxy created by this framework.
     *
     * @param maybeProxy the object to unwrap
     * @return the underlying delegate or the original object
     */
    public Object unwrap(Object maybeProxy) {
        Object current = maybeProxy;
        while (current instanceof ProxyHandle proxyHandle) {
            Object next = proxyHandle.delegate();
            if (next == current) {
                break;
            }
            current = next;
        }
        return current;
    }

    /**
     * Purges stale cache entries whose delegate or proxy was garbage collected.
     */
    public void purgeStaleEntries() {
        synchronized (cacheMonitor) {
            cacheEntries.removeIf(CacheEntry::stale);
        }
    }

    /**
     * Returns the current cache size after purging stale entries.
     *
     * @return the number of live cache entries
     */
    public int cachedProxyCount() {
        purgeStaleEntries();
        synchronized (cacheMonitor) {
            return cacheEntries.size();
        }
    }

    private static boolean supportsAll(Object proxy, Class<?> iface, Class<?>[] additionalInterfaces) {
        if (!iface.isInstance(proxy)) {
            return false;
        }
        for (Class<?> additionalInterface : additionalInterfaces) {
            if (!additionalInterface.isInstance(proxy)) {
                return false;
            }
        }
        return true;
    }

    private Object tryReturnExistingProxy(Object delegate, Class<?> iface, Class<?>[] additionalInterfaces) {
        if (delegate instanceof ProxyHandle && supportsAll(delegate, iface, additionalInterfaces)) {
            return delegate;
        }
        return null;
    }

    private static ClassLoader resolveClassLoader(Object delegate, Class<?> iface, Set<Class<?>> interfaces) {
        ClassLoader loader = ProxyHandle.class.getClassLoader();
        if (loader != null) {
            return loader;
        }
        loader = delegate.getClass().getClassLoader();
        if (loader != null) {
            return loader;
        }
        loader = iface.getClassLoader();
        if (loader != null) {
            return loader;
        }
        for (Class<?> candidate : interfaces) {
            if (candidate.getClassLoader() != null) {
                return candidate.getClassLoader();
            }
        }
        return null;
    }

    private static final class CacheEntry {
        private final WeakReference<Object> delegateReference;
        private final WeakReference<Object> proxyReference;

        private CacheEntry(Object delegate, Object proxy) {
            this.delegateReference = new WeakReference<>(delegate);
            this.proxyReference = new WeakReference<>(proxy);
        }

        private boolean matches(Object delegate) {
            return delegateReference.get() == delegate;
        }

        private Object proxy() {
            return proxyReference.get();
        }

        private boolean stale() {
            return delegateReference.get() == null || proxyReference.get() == null;
        }
    }

    private static final class FrameworkInvocationHandler implements InvocationHandler {
        private final Object delegate;
        private final Class<?> interfaceType;
        private final Object parent;
        private final ProxyContext proxyContext;
        private final ReturnValueAdapter returnValueAdapter;
        private final ArgumentAdapter argumentAdapter;

        private FrameworkInvocationHandler(Object delegate, Class<?> interfaceType, Object parent,
                ProxyContext proxyContext, ReturnValueAdapter returnValueAdapter,
                ArgumentAdapter argumentAdapter) {
            this.delegate = delegate;
            this.interfaceType = interfaceType;
            this.parent = parent;
            this.proxyContext = proxyContext;
            this.returnValueAdapter = returnValueAdapter;
            this.argumentAdapter = argumentAdapter;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getDeclaringClass() == ProxyHandle.class) {
                return switch (method.getName()) {
                    case "delegate" -> delegate;
                    case "proxyContext" -> proxyContext;
                    default -> throw new IllegalStateException("Unknown proxy handle method: " + method);
                };
            }
            if (method.getDeclaringClass() == Object.class) {
                return handleObjectMethod(proxy, method, args);
            }

            InvocationContext invocation = new InvocationContext(proxy, delegate, method, args,
                    proxyContext.resourceName(), parent, interfaceType);
            InvocationChain chain = buildChain();
            return chain.proceed(invocation);
        }

        private InvocationChain buildChain() {
            InvocationChain chain = this::invokeTerminal;
            List<InvocationFilter> filters = proxyContext.filters();
            for (int index = filters.size() - 1; index >= 0; index--) {
                InvocationFilter filter = filters.get(index);
                InvocationChain next = chain;
                chain = invocation -> filter.intercept(invocation, next);
            }
            return chain;
        }

        private Object invokeTerminal(InvocationContext invocation) throws Throwable {
            Object[] adaptedArguments = invocation.arguments();
            if (argumentAdapter != null) {
                adaptedArguments = argumentAdapter.adapt(invocation, adaptedArguments, proxyContext);
                invocation.replaceArguments(adaptedArguments);
                adaptedArguments = invocation.arguments();
            }
            Object result = invokeDelegate(invocation.method(), adaptedArguments);
            if (returnValueAdapter != null) {
                return returnValueAdapter.adapt(invocation, result, proxyContext);
            }
            return result;
        }

        private Object invokeDelegate(Method method, Object[] arguments) throws Throwable {
            try {
                if (shouldInvokeDefaultMethod(method)) {
                    return invokeDefaultMethod(method, arguments);
                }
                return method.invoke(delegate, arguments);
            } catch (InvocationTargetException ex) {
                throw ex.getCause();
            }
        }

        private boolean shouldInvokeDefaultMethod(Method method) {
            if (!method.isDefault()) {
                return false;
            }
            try {
                Method resolved = delegate.getClass().getMethod(method.getName(), method.getParameterTypes());
                return resolved.getDeclaringClass().isInterface() && resolved.isDefault();
            } catch (NoSuchMethodException ex) {
                return true;
            }
        }

        private Object invokeDefaultMethod(Method method, Object[] arguments) throws Throwable {
            Class<?> declaringClass = method.getDeclaringClass();
            MethodHandles.Lookup lookup = MethodHandles.privateLookupIn(declaringClass, MethodHandles.lookup());
            MethodHandle handle = lookup.findSpecial(declaringClass, method.getName(),
                    MethodType.methodType(method.getReturnType(), method.getParameterTypes()), declaringClass);
            return handle.bindTo(delegate).invokeWithArguments(arguments == null ? List.of() : Arrays.asList(arguments));
        }

        private Object handleObjectMethod(Object proxy, Method method, Object[] args) {
            return switch (method.getName()) {
                case "equals" -> proxy == args[0] || (args[0] instanceof ProxyHandle
                        && delegate == proxyContext.unwrap(args[0]));
                case "hashCode" -> System.identityHashCode(delegate);
                case "toString" -> "InterfaceProxy[interface=" + interfaceType.getName()
                        + ", delegateClass=" + delegate.getClass().getName()
                        + ", resourceName=" + proxyContext.resourceName() + "]";
                default -> throw new IllegalStateException("Unsupported Object method: " + method);
            };
        }
    }
}
