package io.github.rrobetti.japiproxy.core;

/**
 * Adapts invocation arguments before delegate invocation.
 */
@FunctionalInterface
public interface ArgumentAdapter {
    /**
     * Adapts invocation arguments.
     *
     * @param invocation the invocation state
     * @param arguments the current arguments
     * @param proxyContext the shared proxy context
     * @return the adapted arguments
     * @throws Throwable when adaptation fails
     */
    Object[] adapt(InvocationContext invocation, Object[] arguments, ProxyContext proxyContext) throws Throwable;
}
