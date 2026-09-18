package io.github.rrobetti.japiproxy;

import io.github.rrobetti.japiproxy.core.InvocationChain;
import io.github.rrobetti.japiproxy.core.InvocationContext;
import io.github.rrobetti.japiproxy.core.InvocationFilter;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Example {@link InvocationFilter} that records success/failure counts and total elapsed time
 * per invoked method. The same instance can be attached to JDBC, Jakarta JMS, and XA proxies
 * built with this library, since it only depends on the generic {@link InvocationContext} API.
 *
 * <p>This is a demonstration filter; production users are expected to integrate with their own
 * metrics system (e.g. Micrometer) instead of relying on this simple in-memory implementation.
 */
public final class MonitoringFilter implements InvocationFilter {
    private final ConcurrentMap<String, LongAdder> successCounts = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, LongAdder> failureCounts = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, AtomicLong> totalNanos = new ConcurrentHashMap<>();

    @Override
    public Object intercept(InvocationContext invocation, InvocationChain chain) throws Throwable {
        long started = System.nanoTime();
        String key = key(invocation);
        try {
            Object result = chain.proceed(invocation);
            successCounts.computeIfAbsent(key, unused -> new LongAdder()).increment();
            totalNanos.computeIfAbsent(key, unused -> new AtomicLong()).addAndGet(System.nanoTime() - started);
            return result;
        } catch (Throwable failure) {
            failureCounts.computeIfAbsent(key, unused -> new LongAdder()).increment();
            throw failure;
        }
    }

    /**
     * Returns the number of successful invocations recorded for the given method key.
     *
     * @param interfaceType the logical interface
     * @param methodName the method name
     * @return the success count
     */
    public long successCount(Class<?> interfaceType, String methodName) {
        LongAdder adder = successCounts.get(key(interfaceType, methodName));
        return adder == null ? 0L : adder.sum();
    }

    /**
     * Returns the number of failed invocations recorded for the given method key.
     *
     * @param interfaceType the logical interface
     * @param methodName the method name
     * @return the failure count
     */
    public long failureCount(Class<?> interfaceType, String methodName) {
        LongAdder adder = failureCounts.get(key(interfaceType, methodName));
        return adder == null ? 0L : adder.sum();
    }

    private static String key(InvocationContext invocation) {
        return key(invocation.interfaceType(), invocation.method().getName());
    }

    private static String key(Class<?> interfaceType, String methodName) {
        return interfaceType.getName() + "#" + methodName;
    }
}
