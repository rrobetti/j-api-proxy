package io.github.rrobetti.japiproxy.core;

/**
 * Adapts return values after delegate invocation.
 */
@FunctionalInterface
public interface ReturnValueAdapter {
    /**
     * Adapts a return value.
     *
     * @param invocation the invocation state
     * @param returnedValue the raw return value
     * @param proxyContext the shared proxy context
     * @return the adapted return value
     * @throws Throwable when adaptation fails
     */
    Object adapt(InvocationContext invocation, Object returnedValue, ProxyContext proxyContext) throws Throwable;
}
