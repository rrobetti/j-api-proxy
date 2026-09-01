package io.github.rrobetti.japiproxy.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Entry point for building JDK dynamic proxies backed by invocation filters.
 */
public final class InterfaceProxy {
    private InterfaceProxy() {
    }

    /**
     * Creates a new builder for a delegate.
     *
     * @param interfaceType the primary proxied interface
     * @param delegate the wrapped delegate
     * @param <T> the proxied type
     * @return the proxy builder
     */
    public static <T> Builder<T> builder(Class<T> interfaceType, T delegate) {
        return new Builder<>(interfaceType, delegate);
    }

    /**
     * Recursively unwraps a proxy created by this framework.
     *
     * @param possiblyProxy the object to unwrap
     * @return the original delegate, or the original object when not proxied
     */
    public static Object unwrap(Object possiblyProxy) {
        Object current = possiblyProxy;
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
     * Builder for interface proxies.
     *
     * @param <T> the proxied type
     */
    public static final class Builder<T> {
        private final Class<T> interfaceType;
        private final T delegate;
        private final List<InvocationFilter> filters = new ArrayList<>();
        private final List<Class<?>> additionalInterfaces = new ArrayList<>();
        private String resourceName;
        private ReturnValueAdapter returnValueAdapter;
        private ArgumentAdapter argumentAdapter;
        private ProxyContext proxyContext;

        private Builder(Class<T> interfaceType, T delegate) {
            this.interfaceType = Objects.requireNonNull(interfaceType, "interfaceType");
            this.delegate = Objects.requireNonNull(delegate, "delegate");
        }

        /**
         * Sets the logical resource name.
         *
         * @param resourceName the resource name
         * @return this builder
         */
        public Builder<T> resourceName(String resourceName) {
            this.resourceName = resourceName;
            return this;
        }

        /**
         * Adds an invocation filter.
         *
         * <p>Filters added here are ignored when {@link #proxyContext(ProxyContext)} is also used,
         * because a shared {@link ProxyContext} already owns its own filter chain. Combining the two
         * throws {@link IllegalStateException} at {@link #build()} time instead of silently dropping
         * filters; add filters directly to the shared {@link ProxyContext} instead.
         *
         * @param filter the filter to add
         * @return this builder
         */
        public Builder<T> filter(InvocationFilter filter) {
            this.filters.add(Objects.requireNonNull(filter, "filter"));
            return this;
        }

        /**
         * Sets the return value adapter.
         *
         * @param returnValueAdapter the adapter
         * @return this builder
         */
        public Builder<T> returnValueAdapter(ReturnValueAdapter returnValueAdapter) {
            this.returnValueAdapter = returnValueAdapter;
            return this;
        }

        /**
         * Sets the argument adapter.
         *
         * @param argumentAdapter the adapter
         * @return this builder
         */
        public Builder<T> argumentAdapter(ArgumentAdapter argumentAdapter) {
            this.argumentAdapter = argumentAdapter;
            return this;
        }

        /**
         * Adds additional interfaces implemented by the proxy.
         *
         * @param interfaces additional interfaces
         * @return this builder
         */
        public Builder<T> additionalInterfaces(Class<?>... interfaces) {
            if (interfaces != null) {
                this.additionalInterfaces.addAll(Arrays.asList(interfaces));
            }
            return this;
        }

        /**
         * Shares an existing proxy context.
         *
         * @param proxyContext the shared context
         * @return this builder
         */
        public Builder<T> proxyContext(ProxyContext proxyContext) {
            this.proxyContext = proxyContext;
            return this;
        }

        /**
         * Builds the proxy.
         *
         * @return the created proxy
         * @throws IllegalStateException if both {@link #filter(InvocationFilter)} and
         *         {@link #proxyContext(ProxyContext)} were used, since the shared context's own
         *         filter chain would otherwise silently take precedence over builder-supplied filters
         */
        public T build() {
            if (proxyContext != null && !filters.isEmpty()) {
                throw new IllegalStateException(
                        "Builder.filter(...) cannot be combined with an explicit proxyContext(...); "
                                + "add filters to the shared ProxyContext instead.");
            }
            ProxyContext context = proxyContext != null ? proxyContext : new ProxyContext(resourceName, filters);
            return context.wrap(delegate, interfaceType, null, returnValueAdapter, argumentAdapter,
                    additionalInterfaces.toArray(Class<?>[]::new));
        }
    }
}
