package io.github.rrobetti.japiproxy.core;

/**
 * Intercepts an invocation before it reaches the delegate.
 */
@FunctionalInterface
public interface InvocationFilter {
    /**
     * Intercepts an invocation.
     *
     * @param invocation the invocation state
     * @param chain the remaining invocation chain
     * @return the invocation result
     * @throws Throwable when invocation fails
     */
    Object intercept(InvocationContext invocation, InvocationChain chain) throws Throwable;
}
