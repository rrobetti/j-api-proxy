package io.github.rrobetti.japiproxy.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

class InterfaceProxyTest {
    @Test
    void filtersRunInInsertionOrder() {
        List<String> events = new ArrayList<>();
        EchoService proxy = InterfaceProxy.builder(EchoService.class, new EchoServiceImpl())
                .filter((invocation, chain) -> {
                    events.add("first-before");
                    Object result = chain.proceed(invocation);
                    events.add("first-after");
                    return result;
                })
                .filter((invocation, chain) -> {
                    events.add("second-before");
                    Object result = chain.proceed(invocation);
                    events.add("second-after");
                    return result;
                })
                .build();

        assertEquals("hello", proxy.echo("hello"));
        assertEquals(List.of("first-before", "second-before", "second-after", "first-after"), events);
    }

    @Test
    void argumentsCanBeModified() {
        EchoService proxy = InterfaceProxy.builder(EchoService.class, new EchoServiceImpl())
                .filter((invocation, chain) -> {
                    invocation.replaceArguments(new Object[] {"changed"});
                    return chain.proceed(invocation);
                })
                .build();

        assertEquals("changed", proxy.echo("ignored"));
    }

    @Test
    void resultCanBeModified() {
        EchoService proxy = InterfaceProxy.builder(EchoService.class, new EchoServiceImpl())
                .filter((invocation, chain) -> chain.proceed(invocation) + "-filtered")
                .returnValueAdapter((invocation, value, context) -> value + "-adapted")
                .build();

        assertEquals("hello-adapted-filtered", proxy.echo("hello"));
    }

    @Test
    void filtersCanShortCircuit() {
        EchoService proxy = InterfaceProxy.builder(EchoService.class, new EchoServiceImpl())
                .filter((invocation, chain) -> "short-circuit")
                .build();

        assertEquals("short-circuit", proxy.echo("hello"));
    }

    @Test
    void filtersCanReplaceExceptionsAndThrowOnNormalResults() {
        CheckedService proxy = InterfaceProxy.builder(CheckedService.class, new CheckedServiceImpl())
                .filter((invocation, chain) -> {
                    try {
                        return chain.proceed(invocation);
                    } catch (IOException ex) {
                        throw new IllegalStateException("translated", ex);
                    }
                })
                .build();

        IllegalStateException failure = assertThrows(IllegalStateException.class, proxy::fail);
        assertEquals("translated", failure.getMessage());

        EchoService throwingProxy = InterfaceProxy.builder(EchoService.class, new EchoServiceImpl())
                .filter((invocation, chain) -> {
                    chain.proceed(invocation);
                    throw new IllegalArgumentException("result rejected");
                })
                .build();

        IllegalArgumentException replaced = assertThrows(IllegalArgumentException.class,
                () -> throwingProxy.echo("hello"));
        assertEquals("result rejected", replaced.getMessage());
    }

    @Test
    void filtersCanObserveBeforeAndAfterExecution() {
        List<String> events = new ArrayList<>();
        EchoService proxy = InterfaceProxy.builder(EchoService.class, new EchoServiceImpl())
                .filter((invocation, chain) -> {
                    events.add("before");
                    try {
                        return chain.proceed(invocation);
                    } finally {
                        events.add("after");
                    }
                })
                .build();

        proxy.echo("hello");
        assertEquals(List.of("before", "after"), events);
    }

    @Test
    void proxyCanExposeMultipleInterfaces() throws Exception {
        RunnableCallable delegate = new RunnableCallable();
        Runnable runnable = InterfaceProxy.builder(Runnable.class, delegate)
                .additionalInterfaces(Callable.class)
                .build();

        runnable.run();
        assertEquals("called", ((Callable<String>) runnable).call());
        assertTrue(runnable instanceof Callable);
    }

    @Test
    void checkedExceptionsPropagateUnwrapped() {
        CheckedService proxy = InterfaceProxy.builder(CheckedService.class, new CheckedServiceImpl()).build();

        IOException failure = assertThrows(IOException.class, proxy::fail);
        assertEquals("boom", failure.getMessage());
    }

    @Test
    void delegatesCanBeUnwrapped() {
        EchoServiceImpl delegate = new EchoServiceImpl();
        EchoService proxy = InterfaceProxy.builder(EchoService.class, delegate).build();

        assertSame(delegate, InterfaceProxy.unwrap(proxy));
    }

    @Test
    void sharedProxyContextProvidesStableIdentity() {
        ProxyContext proxyContext = new ProxyContext("shared", List.of());
        EchoServiceImpl delegate = new EchoServiceImpl();

        EchoService first = InterfaceProxy.builder(EchoService.class, delegate).proxyContext(proxyContext).build();
        EchoService second = InterfaceProxy.builder(EchoService.class, delegate).proxyContext(proxyContext).build();

        assertSame(first, second);
    }

    @Test
    void concurrentInvocationsRemainSafe() throws Exception {
        CountingService proxy = InterfaceProxy.builder(CountingService.class, new CountingServiceImpl()).build();
        ExecutorService executor = Executors.newFixedThreadPool(6);
        CountDownLatch latch = new CountDownLatch(1);
        try {
            List<Future<Integer>> futures = new ArrayList<>();
            for (int index = 0; index < 24; index++) {
                futures.add(executor.submit(() -> {
                    latch.await();
                    return proxy.incrementAndGet();
                }));
            }
            latch.countDown();
            List<Integer> results = new ArrayList<>();
            for (Future<Integer> future : futures) {
                results.add(future.get(5, TimeUnit.SECONDS));
            }
            assertEquals(24, results.size());
            assertEquals(25, proxy.incrementAndGet());
        } finally {
            executor.shutdownNow();
        }
    }

    @Test
    void wrappingAnExistingProxyReturnsTheSameProxy() {
        EchoServiceImpl delegate = new EchoServiceImpl();
        EchoService proxy = InterfaceProxy.builder(EchoService.class, delegate).build();
        EchoService wrappedAgain = InterfaceProxy.builder(EchoService.class, proxy).build();

        assertSame(proxy, wrappedAgain);
        assertSame(delegate, InterfaceProxy.unwrap(wrappedAgain));
    }

    @Test
    void staleEntriesCanBePurged() throws Exception {
        ProxyContext proxyContext = new ProxyContext("weak", List.of());
        WeakReference<Object> delegateReference;
        WeakReference<Object> proxyReference;
        {
            EchoServiceImpl delegate = new EchoServiceImpl();
            EchoService proxy = InterfaceProxy.builder(EchoService.class, delegate).proxyContext(proxyContext).build();
            delegateReference = new WeakReference<>(delegate);
            proxyReference = new WeakReference<>(proxy);
            assertEquals(1, proxyContext.cachedProxyCount());
        }

        for (int index = 0; index < 20 && (delegateReference.get() != null || proxyReference.get() != null); index++) {
            System.gc();
            Thread.sleep(25L);
            proxyContext.purgeStaleEntries();
        }

        if (delegateReference.get() == null || proxyReference.get() == null) {
            assertEquals(0, proxyContext.cachedProxyCount());
        } else {
            assertEquals(1, proxyContext.cachedProxyCount());
        }
    }

    @Test
    void equalsHashCodeAndToStringAreSensible() {
        EchoServiceImpl delegate = new EchoServiceImpl();
        ProxyContext proxyContext = new ProxyContext("resource-A", List.of());
        EchoService first = InterfaceProxy.builder(EchoService.class, delegate).proxyContext(proxyContext).build();
        EchoService second = InterfaceProxy.builder(EchoService.class, delegate).proxyContext(proxyContext).build();
        EchoService third = InterfaceProxy.builder(EchoService.class, new EchoServiceImpl()).build();

        assertEquals(first, second);
        assertEquals(System.identityHashCode(delegate), first.hashCode());
        assertNotSame(first, third);
        assertFalse(first.equals(third));
        assertTrue(first.toString().contains("resource-A"));
        assertTrue(first.toString().contains(EchoServiceImpl.class.getName()));
        assertNotNull(first.toString());
    }

    interface EchoService {
        String echo(String value);
    }

    interface CheckedService {
        void fail() throws IOException;
    }

    interface CountingService {
        int incrementAndGet();
    }

    static final class EchoServiceImpl implements EchoService {
        @Override
        public String echo(String value) {
            return value;
        }
    }

    static final class CheckedServiceImpl implements CheckedService {
        @Override
        public void fail() throws IOException {
            throw new IOException("boom");
        }
    }

    static final class CountingServiceImpl implements CountingService {
        private final AtomicInteger counter = new AtomicInteger();

        @Override
        public int incrementAndGet() {
            return counter.incrementAndGet();
        }
    }

    static final class RunnableCallable implements Runnable, Callable<String> {
        @Override
        public void run() {
        }

        @Override
        public String call() {
            return "called";
        }
    }
}
