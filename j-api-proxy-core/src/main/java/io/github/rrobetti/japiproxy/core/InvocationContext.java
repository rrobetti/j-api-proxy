package io.github.rrobetti.japiproxy.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Mutable state for a single intercepted invocation.
 */
public final class InvocationContext {
    private final Object proxy;
    private final Object delegate;
    private final Method method;
    private Object[] arguments;
    private final Class<?> returnType;
    private final Map<String, Object> attributes = new HashMap<>();
    private final String resourceName;
    private final Object parent;
    private final Class<?> interfaceType;

    /**
     * Creates a new invocation context.
     *
     * @param proxy the proxy receiving the call
     * @param delegate the underlying delegate
     * @param method the invoked method
     * @param arguments the current invocation arguments
     * @param resourceName the logical resource name, if any
     * @param parent the parent proxy or object, if any
     * @param interfaceType the intercepted interface type
     */
    public InvocationContext(Object proxy, Object delegate, Method method, Object[] arguments,
            String resourceName, Object parent, Class<?> interfaceType) {
        this.proxy = proxy;
        this.delegate = delegate;
        this.method = Objects.requireNonNull(method, "method");
        this.arguments = arguments == null ? new Object[0] : arguments.clone();
        this.returnType = method.getReturnType();
        this.resourceName = resourceName;
        this.parent = parent;
        this.interfaceType = Objects.requireNonNull(interfaceType, "interfaceType");
    }

    /**
     * Returns the proxy receiving the invocation.
     *
     * @return the proxy object
     */
    public Object proxy() {
        return proxy;
    }

    /**
     * Returns the wrapped delegate.
     *
     * @return the original delegate
     */
    public Object delegate() {
        return delegate;
    }

    /**
     * Returns the invoked method.
     *
     * @return the invoked method
     */
    public Method method() {
        return method;
    }

    /**
     * Returns the current invocation arguments.
     *
     * @return the mutable arguments array
     */
    public Object[] arguments() {
        return arguments;
    }

    /**
     * Replaces the invocation arguments.
     *
     * @param arguments the new arguments
     */
    public void replaceArguments(Object[] arguments) {
        this.arguments = arguments == null ? new Object[0] : arguments.clone();
    }

    /**
     * Returns the declared return type.
     *
     * @return the declared return type
     */
    public Class<?> returnType() {
        return returnType;
    }

    /**
     * Returns per-invocation mutable attributes.
     *
     * @return the mutable attribute map
     */
    public Map<String, Object> attributes() {
        return attributes;
    }

    /**
     * Returns the logical resource name.
     *
     * @return the resource name, or {@code null}
     */
    public String resourceName() {
        return resourceName;
    }

    /**
     * Returns the parent proxy or object.
     *
     * @return the parent, or {@code null}
     */
    public Object parent() {
        return parent;
    }

    /**
     * Returns the intercepted logical interface type.
     *
     * @return the intercepted interface type
     */
    public Class<?> interfaceType() {
        return interfaceType;
    }
}
