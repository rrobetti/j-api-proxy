package io.github.rrobetti.japiproxy.jms;

import io.github.rrobetti.japiproxy.core.ArgumentAdapter;
import io.github.rrobetti.japiproxy.core.InterfaceProxy;
import io.github.rrobetti.japiproxy.core.InvocationContext;
import io.github.rrobetti.japiproxy.core.InvocationFilter;
import io.github.rrobetti.japiproxy.core.ProxyHandle;
import io.github.rrobetti.japiproxy.core.ProxyContext;
import io.github.rrobetti.japiproxy.core.ReturnValueAdapter;
import jakarta.jms.CompletionListener;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.ExceptionListener;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageListener;
import jakarta.jms.MessageProducer;
import jakarta.jms.QueueBrowser;
import jakarta.jms.Session;
import jakarta.jms.TemporaryQueue;
import jakarta.jms.TemporaryTopic;
import jakarta.jms.XAConnection;
import jakarta.jms.XAConnectionFactory;
import jakarta.jms.XAJMSContext;
import jakarta.jms.XASession;

import javax.transaction.xa.XAResource;
import java.util.Objects;

/**
 * Proxies Jakarta JMS ({@code jakarta.jms.*}) object graphs through {@code j-api-proxy-core}.
 * This module intentionally targets Jakarta JMS only so a sibling legacy {@code javax.jms} adapter
 * can be introduced later without changing the core proxy infrastructure.
 */
public final class JmsProxy {
    private static final JmsProxyOptions DEFAULT_OPTIONS = JmsProxyOptions.builder().build();
    private static final JmsArgumentAdapter ARGUMENT_ADAPTER = new JmsArgumentAdapter();

    private JmsProxy() {
    }

    public static ConnectionFactory wrap(ConnectionFactory delegate, String resourceName,
            InvocationFilter... filters) {
        return wrap(delegate, resourceName, DEFAULT_OPTIONS, filters);
    }

    public static ConnectionFactory wrap(ConnectionFactory delegate, String resourceName,
            JmsProxyOptions options, InvocationFilter... filters) {
        return buildRootProxy(ConnectionFactory.class, delegate, resourceName, options, filters);
    }

    public static XAConnectionFactory wrapXa(XAConnectionFactory delegate, String resourceName,
            InvocationFilter... filters) {
        return wrapXa(delegate, resourceName, DEFAULT_OPTIONS, filters);
    }

    public static XAConnectionFactory wrapXa(XAConnectionFactory delegate, String resourceName,
            JmsProxyOptions options, InvocationFilter... filters) {
        return buildRootProxy(XAConnectionFactory.class, delegate, resourceName, options, filters);
    }

    private static <T> T buildRootProxy(Class<T> interfaceType, T delegate, String resourceName,
            JmsProxyOptions options, InvocationFilter... filters) {
        Objects.requireNonNull(delegate, "delegate");
        JmsProxyOptions resolvedOptions = options == null ? DEFAULT_OPTIONS : options;
        JmsReturnValueAdapter returnValueAdapter = new JmsReturnValueAdapter(resolvedOptions);

        InterfaceProxy.Builder<T> builder = InterfaceProxy.builder(interfaceType, delegate)
                .resourceName(resourceName)
                .returnValueAdapter(returnValueAdapter)
                .argumentAdapter(ARGUMENT_ADAPTER);
        if (filters != null) {
            for (InvocationFilter filter : filters) {
                builder.filter(Objects.requireNonNull(filter, "filter"));
            }
        }
        return builder.build();
    }

    private static final class JmsReturnValueAdapter implements ReturnValueAdapter {
        private final JmsProxyOptions options;

        private JmsReturnValueAdapter(JmsProxyOptions options) {
            this.options = options;
        }

        @Override
        public Object adapt(InvocationContext invocation, Object returnedValue, ProxyContext proxyContext) {
            if (returnedValue == null) {
                return null;
            }
            if (returnedValue instanceof XAConnectionFactory factory && options.connections()) {
                return wrap(proxyContext, invocation.proxy(), factory, XAConnectionFactory.class, true);
            }
            if (returnedValue instanceof XAConnection connection && options.connections()) {
                return wrap(proxyContext, invocation.proxy(), connection, XAConnection.class, true);
            }
            if (returnedValue instanceof XAJMSContext context && options.connections()) {
                return wrap(proxyContext, invocation.proxy(), context, XAJMSContext.class, true);
            }
            if (returnedValue instanceof XASession session && options.sessions()) {
                return wrap(proxyContext, invocation.proxy(), session, XASession.class, true);
            }
            if (returnedValue instanceof XAResource resource) {
                return wrap(proxyContext, invocation.proxy(), resource, XAResource.class, false);
            }
            if (returnedValue instanceof ConnectionFactory factory && options.connections()) {
                return wrap(proxyContext, invocation.proxy(), factory, ConnectionFactory.class, true);
            }
            if (returnedValue instanceof Connection connection && options.connections()) {
                return wrap(proxyContext, invocation.proxy(), connection, Connection.class, true);
            }
            if (returnedValue instanceof JMSContext context && options.connections()) {
                return wrap(proxyContext, invocation.proxy(), context, JMSContext.class, true);
            }
            if (returnedValue instanceof Session session && options.sessions()) {
                return wrap(proxyContext, invocation.proxy(), session, Session.class, true);
            }
            if (returnedValue instanceof MessageProducer producer && options.consumersProducers()) {
                return wrap(proxyContext, invocation.proxy(), producer, MessageProducer.class, true);
            }
            if (returnedValue instanceof MessageConsumer consumer && options.consumersProducers()) {
                return wrap(proxyContext, invocation.proxy(), consumer, MessageConsumer.class, true);
            }
            if (returnedValue instanceof QueueBrowser browser && options.sessions()) {
                return wrap(proxyContext, invocation.proxy(), browser, QueueBrowser.class, true);
            }
            if (returnedValue instanceof TemporaryQueue queue && options.sessions()) {
                return wrap(proxyContext, invocation.proxy(), queue, TemporaryQueue.class, true);
            }
            if (returnedValue instanceof TemporaryTopic topic && options.sessions()) {
                return wrap(proxyContext, invocation.proxy(), topic, TemporaryTopic.class, true);
            }
            if (returnedValue instanceof JMSProducer producer && options.consumersProducers()) {
                return wrap(proxyContext, invocation.proxy(), producer, JMSProducer.class, true);
            }
            if (returnedValue instanceof JMSConsumer consumer && options.consumersProducers()) {
                return wrap(proxyContext, invocation.proxy(), consumer, JMSConsumer.class, true);
            }
            return returnedValue;
        }

        private <T> T wrap(ProxyContext proxyContext, Object parent, T delegate, Class<T> type,
                boolean adaptRecursively) {
            ReturnValueAdapter returnValueAdapter = adaptRecursively ? this : null;
            ArgumentAdapter argumentAdapter = adaptRecursively ? ARGUMENT_ADAPTER : null;
            return proxyContext.wrap(delegate, type, parent, returnValueAdapter, argumentAdapter);
        }
    }

    private static final class JmsArgumentAdapter implements ArgumentAdapter {
        @Override
        public Object[] adapt(InvocationContext invocation, Object[] arguments, ProxyContext proxyContext) {
            if (arguments == null || arguments.length == 0) {
                return arguments;
            }
            Object[] adapted = arguments.clone();
            Class<?>[] parameterTypes = invocation.method().getParameterTypes();
            for (int index = 0; index < parameterTypes.length && index < adapted.length; index++) {
                Object argument = adapted[index];
                if (argument == null || argument instanceof ProxyHandle) {
                    continue;
                }
                Class<?> parameterType = parameterTypes[index];
                if (parameterType == MessageListener.class) {
                    adapted[index] = proxyContext.wrap(argument, MessageListener.class, invocation.proxy(), null, null);
                } else if (parameterType == CompletionListener.class) {
                    adapted[index] = proxyContext.wrap(argument, CompletionListener.class, invocation.proxy(), null, null);
                } else if (parameterType == ExceptionListener.class) {
                    adapted[index] = proxyContext.wrap(argument, ExceptionListener.class, invocation.proxy(), null, null);
                }
            }
            return adapted;
        }
    }
}
