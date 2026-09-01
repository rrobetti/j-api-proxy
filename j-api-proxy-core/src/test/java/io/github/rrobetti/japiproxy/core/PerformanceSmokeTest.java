package io.github.rrobetti.japiproxy.core;

import org.junit.jupiter.api.Test;

/**
 * A simple, repeatable performance smoke test measuring the approximate overhead of
 * {@link InterfaceProxy} with no filters, one no-op filter, and several filters, compared to a
 * direct (unproxied) call.
 *
 * <p>This is not a rigorous JMH benchmark. It exists to give an order-of-magnitude signal and to
 * catch gross performance regressions; absolute numbers will vary across machines and JVMs, and
 * no production-suitability claims should be inferred from it.
 */
class PerformanceSmokeTest {
    private static final int WARMUP_ITERATIONS = 20_000;
    private static final int MEASURED_ITERATIONS = 200_000;

    interface Counter {
        int increment(int value);
    }

    static final class CounterImpl implements Counter {
        @Override
        public int increment(int value) {
            return value + 1;
        }
    }

    @Test
    void reportsApproximateOverheadOfFilterChains() {
        Counter direct = new CounterImpl();
        Counter noFilters = InterfaceProxy.builder(Counter.class, new CounterImpl()).build();
        Counter oneNoOpFilter = InterfaceProxy.builder(Counter.class, new CounterImpl())
                .filter((invocation, chain) -> chain.proceed(invocation))
                .build();
        Counter severalFilters = InterfaceProxy.builder(Counter.class, new CounterImpl())
                .filter((invocation, chain) -> chain.proceed(invocation))
                .filter((invocation, chain) -> chain.proceed(invocation))
                .filter((invocation, chain) -> chain.proceed(invocation))
                .filter((invocation, chain) -> chain.proceed(invocation))
                .filter((invocation, chain) -> chain.proceed(invocation))
                .build();

        long directNanos = measure(direct);
        long noFiltersNanos = measure(noFilters);
        long oneFilterNanos = measure(oneNoOpFilter);
        long severalFiltersNanos = measure(severalFilters);

        System.out.printf(
                "Performance smoke test (%,d iterations): direct=%dns/op, proxy-no-filters=%dns/op, "
                        + "proxy-one-filter=%dns/op, proxy-five-filters=%dns/op%n",
                MEASURED_ITERATIONS,
                directNanos / MEASURED_ITERATIONS,
                noFiltersNanos / MEASURED_ITERATIONS,
                oneFilterNanos / MEASURED_ITERATIONS,
                severalFiltersNanos / MEASURED_ITERATIONS);

        // No hard assertions on absolute timings (too environment-dependent); the test's value is
        // the printed report plus confirming that all proxy variants execute without error.
    }

    private static long measure(Counter counter) {
        int value = 0;
        for (int i = 0; i < WARMUP_ITERATIONS; i++) {
            value = counter.increment(value);
        }
        long started = System.nanoTime();
        for (int i = 0; i < MEASURED_ITERATIONS; i++) {
            value = counter.increment(value);
        }
        long elapsed = System.nanoTime() - started;
        if (value == Integer.MIN_VALUE) {
            throw new AssertionError("unreachable, prevents JIT from eliminating the loop");
        }
        return elapsed;
    }
}
