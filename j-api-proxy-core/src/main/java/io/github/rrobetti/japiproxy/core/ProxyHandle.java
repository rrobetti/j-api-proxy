package io.github.rrobetti.japiproxy.core;

/**
 * Marker interface implemented by generated proxies.
 */
public interface ProxyHandle {
    /**
     * Returns the wrapped delegate.
     *
     * @return the delegate
     */
    Object delegate();

    /**
     * Returns the shared proxy context.
     *
     * @return the proxy context
     */
    ProxyContext proxyContext();
}
