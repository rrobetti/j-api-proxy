package io.github.rrobetti.japiproxy.core;

/**
 * Represents the remaining invocation chain.
 */
@FunctionalInterface
public interface InvocationChain {
    /**
     * Continues invocation processing.
     *
     * @param invocation the invocation state
     * @return the invocation result
     * @throws Throwable when invocation fails
     */
    Object proceed(InvocationContext invocation) throws Throwable;
}
