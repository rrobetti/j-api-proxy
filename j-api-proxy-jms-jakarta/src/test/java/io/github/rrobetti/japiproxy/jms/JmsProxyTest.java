package io.github.rrobetti.japiproxy.jms;

import io.github.rrobetti.japiproxy.core.InterfaceProxy;
import io.github.rrobetti.japiproxy.core.InvocationChain;
import io.github.rrobetti.japiproxy.core.InvocationContext;
import io.github.rrobetti.japiproxy.core.InvocationFilter;
import io.github.rrobetti.japiproxy.core.ProxyHandle;
import jakarta.jms.BytesMessage;
import jakarta.jms.CompletionListener;
import jakarta.jms.Connection;
import jakarta.jms.ConnectionConsumer;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.ConnectionMetaData;
import jakarta.jms.Destination;
import jakarta.jms.ExceptionListener;
import jakarta.jms.JMSConsumer;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.JMSProducer;
import jakarta.jms.MapMessage;
import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageListener;
import jakarta.jms.MessageProducer;
import jakarta.jms.ObjectMessage;
import jakarta.jms.QueueBrowser;
import jakarta.jms.ServerSessionPool;
import jakarta.jms.Session;
import jakarta.jms.StreamMessage;
import jakarta.jms.TemporaryQueue;
import jakarta.jms.TemporaryTopic;
import jakarta.jms.TextMessage;
import jakarta.jms.Topic;
import jakarta.jms.TopicSubscriber;
import jakarta.jms.XAConnection;
import jakarta.jms.XAConnectionFactory;
import jakarta.jms.XAJMSContext;
import jakarta.jms.XASession;
import org.junit.jupiter.api.Test;

import javax.transaction.xa.XAException;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JmsProxyTest {
    private static final jakarta.jms.Queue TEST_QUEUE = new SimpleQueue("orders");
    private static final Topic TEST_TOPIC = new SimpleTopic("events");
    private static final Destination TEST_DESTINATION = TEST_QUEUE;

    @Test
    void wrapsFactoryConnectionSessionAndSessionChildren() throws JMSException {
        RecordingFilter filter = new RecordingFilter();
        FakeConnectionFactory delegate = new FakeConnectionFactory();

        ConnectionFactory wrapped = JmsProxy.wrap(delegate, "orders", filter);
        Connection connection = wrapped.createConnection();
        Session session = connection.createSession();
        MessageProducer producer = session.createProducer(TEST_DESTINATION);
        MessageConsumer consumer = session.createConsumer(TEST_DESTINATION);
        QueueBrowser browser = session.createBrowser(TEST_QUEUE);
        TemporaryQueue temporaryQueue = session.createTemporaryQueue();
        TemporaryTopic temporaryTopic = session.createTemporaryTopic();

        assertAll(
                () -> assertProxy(delegate.connection, connection),
                () -> assertProxy(delegate.connection.session, session),
                () -> assertProxy(delegate.connection.session.producer, producer),
                () -> assertProxy(delegate.connection.session.consumer, consumer),
                () -> assertProxy(delegate.connection.session.browser, browser),
                () -> assertProxy(delegate.connection.session.temporaryQueue, temporaryQueue),
                () -> assertProxy(delegate.connection.session.temporaryTopic, temporaryTopic),
                () -> assertSame(((ProxyHandle) connection).proxyContext(), ((ProxyHandle) session).proxyContext()),
                () -> assertTrue(filter.contains("ConnectionFactory#createConnection")),
                () -> assertTrue(filter.contains("Connection#createSession"))
        );
    }

    @Test
    void wrapsJmsContextGraph() {
        FakeConnectionFactory delegate = new FakeConnectionFactory();

        JMSContext context = JmsProxy.wrap(delegate, "orders").createContext();
        JMSProducer producer = context.createProducer();
        JMSConsumer consumer = context.createConsumer(TEST_DESTINATION);
        QueueBrowser browser = context.createBrowser(TEST_QUEUE);
        TemporaryQueue temporaryQueue = context.createTemporaryQueue();
        TemporaryTopic temporaryTopic = context.createTemporaryTopic();

        assertAll(
                () -> assertProxy(delegate.context, context),
                () -> assertProxy(delegate.context.producer, producer),
                () -> assertProxy(delegate.context.consumer, consumer),
                () -> assertProxy(delegate.context.browser, browser),
                () -> assertProxy(delegate.context.temporaryQueue, temporaryQueue),
                () -> assertProxy(delegate.context.temporaryTopic, temporaryTopic)
        );
    }

    @Test
    void wrapsXaConnectionTreeAndCachesXaResourcePerSession() throws JMSException {
        FakeXAConnectionFactory delegate = new FakeXAConnectionFactory();

        XAConnectionFactory wrapped = JmsProxy.wrapXa(delegate, "orders-xa");
        XAConnection connection = wrapped.createXAConnection();
        XASession firstSession = connection.createXASession();
        XAResource firstResource = firstSession.getXAResource();
        XAResource firstResourceAgain = firstSession.getXAResource();
        XASession secondSession = connection.createXASession();
        XAResource secondResource = secondSession.getXAResource();
        Session plainSession = connection.createSession();

        assertAll(
                () -> assertProxy(delegate.connection, connection),
                () -> assertProxy(delegate.connection.createdSessions.get(0), firstSession),
                () -> assertProxy(delegate.connection.session, plainSession),
                () -> assertTrue(firstResource instanceof ProxyHandle),
                () -> assertSame(firstResource, firstResourceAgain),
                () -> assertNotSame(firstResource, secondResource)
        );
    }

    @Test
    void wrapsXaJmsContextAndCachesXaResource() {
        FakeXAConnectionFactory delegate = new FakeXAConnectionFactory();

        XAJMSContext context = JmsProxy.wrapXa(delegate, "orders-xa").createXAContext();
        XAResource first = context.getXAResource();
        XAResource second = context.getXAResource();
        JMSContext nestedContext = context.getContext();

        assertAll(
                () -> assertProxy(delegate.context, context),
                () -> assertSame(first, second),
                () -> assertTrue(first instanceof ProxyHandle),
                () -> assertProxy(delegate.context.nestedContext, nestedContext)
        );
    }

    @Test
    void wrapsCallbackArgumentsForListeners() throws JMSException {
        RecordingFilter filter = new RecordingFilter();
        FakeConnectionFactory delegate = new FakeConnectionFactory();
        Connection connection = JmsProxy.wrap(delegate, "orders", filter).createConnection();
        Session session = connection.createSession();
        MessageConsumer consumer = session.createConsumer(TEST_DESTINATION);
        MessageProducer producer = session.createProducer(TEST_DESTINATION);

        AtomicInteger messages = new AtomicInteger();
        AtomicInteger completionSuccess = new AtomicInteger();
        AtomicInteger completionFailures = new AtomicInteger();
        AtomicInteger connectionExceptions = new AtomicInteger();

        MessageListener messageListener = message -> messages.incrementAndGet();
        CompletionListener completionListener = new CompletionListener() {
            @Override
            public void onCompletion(Message message) {
                completionSuccess.incrementAndGet();
            }

            @Override
            public void onException(Message message, Exception exception) {
                completionFailures.incrementAndGet();
            }
        };
        ExceptionListener exceptionListener = exception -> connectionExceptions.incrementAndGet();

        consumer.setMessageListener(messageListener);
        producer.send(message(), completionListener);
        connection.setExceptionListener(exceptionListener);

        delegate.connection.session.consumer.fireMessage();
        delegate.connection.session.producer.fireCompletion();
        delegate.connection.session.producer.fireException(new JMSException("send failed"));
        delegate.connection.fireException(new JMSException("provider failed"));

        assertAll(
                () -> assertNotSame(messageListener, delegate.connection.session.consumer.listener),
                () -> assertNotSame(completionListener, delegate.connection.session.producer.completionListener),
                () -> assertNotSame(exceptionListener, delegate.connection.exceptionListener),
                () -> assertEquals(1, messages.get()),
                () -> assertEquals(1, completionSuccess.get()),
                () -> assertEquals(1, completionFailures.get()),
                () -> assertEquals(1, connectionExceptions.get()),
                () -> assertTrue(filter.contains("MessageListener#onMessage")),
                () -> assertTrue(filter.contains("CompletionListener#onCompletion")),
                () -> assertTrue(filter.contains("CompletionListener#onException")),
                () -> assertTrue(filter.contains("ExceptionListener#onException"))
        );
    }

    @Test
    void passesCredentialsThrough() throws JMSException {
        FakeConnectionFactory delegate = new FakeConnectionFactory();

        Connection connection = JmsProxy.wrap(delegate, "orders").createConnection("alice", "secret");

        assertAll(
                () -> assertProxy(delegate.connection, connection),
                () -> assertEquals("alice", delegate.lastUsername),
                () -> assertEquals("secret", delegate.lastPassword)
        );
    }

    @Test
    void propagatesProviderExceptionsAndClose() throws JMSException {
        FakeConnectionFactory delegate = new FakeConnectionFactory();
        delegate.createConnectionFailure = new JMSException("provider down");
        ConnectionFactory wrapped = JmsProxy.wrap(delegate, "orders");

        JMSException failure = assertThrows(JMSException.class, wrapped::createConnection);
        assertEquals("provider down", failure.getMessage());

        delegate.createConnectionFailure = null;
        Connection connection = wrapped.createConnection();
        Session session = connection.createSession();
        MessageProducer producer = session.createProducer(TEST_DESTINATION);
        connection.close();
        session.close();
        producer.close();

        assertAll(
                () -> assertEquals(1, delegate.connection.closeCalls),
                () -> assertEquals(1, delegate.connection.session.closeCalls),
                () -> assertEquals(1, delegate.connection.session.producer.closeCalls)
        );
    }

    @Test
    void optionsCanDisableDeeperInterception() throws JMSException {
        FakeConnectionFactory delegate = new FakeConnectionFactory();
        JmsProxyOptions options = JmsProxyOptions.builder()
                .sessions(false)
                .consumersProducers(false)
                .build();

        Connection connection = JmsProxy.wrap(delegate, "orders", options).createConnection();
        Session session = connection.createSession();

        assertAll(
                () -> assertProxy(delegate.connection, connection),
                () -> assertSame(delegate.connection.session, session),
                () -> assertFalse(session instanceof ProxyHandle)
        );
    }

    private static void assertProxy(Object delegate, Object proxy) {
        assertAll(
                () -> assertNotSame(delegate, proxy),
                () -> assertTrue(proxy instanceof ProxyHandle),
                () -> assertSame(delegate, InterfaceProxy.unwrap(proxy))
        );
    }

    private static Message message() {
        return proxy(Message.class, Map.of(
                Object.class.getName() + "#toString", "FakeMessage",
                Object.class.getName() + "#hashCode", 7
        ));
    }

    @SuppressWarnings("unchecked")
    private static <T> T proxy(Class<T> type, Map<String, Object> values) {
        return (T) Proxy.newProxyInstance(type.getClassLoader(), new Class<?>[]{type}, (instance, method, args) -> {
            String key = method.getDeclaringClass().getName() + "#" + method.getName();
            if (values.containsKey(key)) {
                return values.get(key);
            }
            if (method.getDeclaringClass() == Object.class) {
                return switch (method.getName()) {
                    case "toString" -> type.getSimpleName() + "Proxy";
                    case "hashCode" -> System.identityHashCode(instance);
                    case "equals" -> instance == args[0];
                    default -> null;
                };
            }
            return defaultValue(method.getReturnType());
        });
    }

    private static Object defaultValue(Class<?> returnType) {
        if (!returnType.isPrimitive()) {
            return null;
        }
        if (returnType == boolean.class) {
            return false;
        }
        if (returnType == byte.class) {
            return (byte) 0;
        }
        if (returnType == short.class) {
            return (short) 0;
        }
        if (returnType == int.class) {
            return 0;
        }
        if (returnType == long.class) {
            return 0L;
        }
        if (returnType == float.class) {
            return 0F;
        }
        if (returnType == double.class) {
            return 0D;
        }
        if (returnType == char.class) {
            return '\0';
        }
        return null;
    }

    private static final class RecordingFilter implements InvocationFilter {
        private final List<String> invocations = new ArrayList<>();

        @Override
        public Object intercept(InvocationContext invocation, InvocationChain chain) throws Throwable {
            invocations.add(invocation.interfaceType().getSimpleName() + "#" + invocation.method().getName());
            return chain.proceed(invocation);
        }

        boolean contains(String entry) {
            return invocations.contains(entry);
        }
    }

    private static final class FakeConnectionFactory implements ConnectionFactory {
        private final FakeConnection connection = new FakeConnection();
        private final FakeJMSContext context = new FakeJMSContext();
        private JMSException createConnectionFailure;
        private String lastUsername;
        private String lastPassword;

        @Override
        public Connection createConnection() throws JMSException {
            if (createConnectionFailure != null) {
                throw createConnectionFailure;
            }
            return connection;
        }

        @Override
        public Connection createConnection(String userName, String password) throws JMSException {
            lastUsername = userName;
            lastPassword = password;
            return createConnection();
        }

        @Override
        public JMSContext createContext() {
            return context;
        }

        @Override
        public JMSContext createContext(String userName, String password) {
            lastUsername = userName;
            lastPassword = password;
            return context;
        }

        @Override
        public JMSContext createContext(String userName, String password, int sessionMode) {
            lastUsername = userName;
            lastPassword = password;
            context.sessionMode = sessionMode;
            return context;
        }

        @Override
        public JMSContext createContext(int sessionMode) {
            context.sessionMode = sessionMode;
            return context;
        }
    }

    private static final class FakeXAConnectionFactory implements XAConnectionFactory {
        private final FakeXAConnection connection = new FakeXAConnection();
        private final FakeXAJMSContext context = new FakeXAJMSContext();

        @Override
        public XAConnection createXAConnection() {
            return connection;
        }

        @Override
        public XAConnection createXAConnection(String userName, String password) {
            connection.lastUsername = userName;
            connection.lastPassword = password;
            return connection;
        }

        @Override
        public XAJMSContext createXAContext() {
            return context;
        }

        @Override
        public XAJMSContext createXAContext(String userName, String password) {
            context.lastUsername = userName;
            context.lastPassword = password;
            return context;
        }
    }

    private static class FakeConnection implements Connection {
        final FakeSession session = new FakeSession();
        private ExceptionListener exceptionListener;
        private int closeCalls;
        private boolean started;
        private String clientId;

        @Override
        public Session createSession(boolean transacted, int acknowledgeMode) {
            session.transacted = transacted;
            session.acknowledgeMode = acknowledgeMode;
            return session;
        }

        @Override
        public Session createSession(int sessionMode) {
            session.transacted = sessionMode == Session.SESSION_TRANSACTED;
            session.acknowledgeMode = sessionMode;
            return session;
        }

        @Override
        public Session createSession() {
            return session;
        }

        @Override
        public String getClientID() {
            return clientId;
        }

        @Override
        public void setClientID(String clientID) {
            this.clientId = clientID;
        }

        @Override
        public ConnectionMetaData getMetaData() {
            return null;
        }

        @Override
        public ExceptionListener getExceptionListener() {
            return exceptionListener;
        }

        @Override
        public void setExceptionListener(ExceptionListener listener) {
            this.exceptionListener = listener;
        }

        @Override
        public void start() {
            started = true;
        }

        @Override
        public void stop() {
            started = false;
        }

        @Override
        public void close() {
            closeCalls++;
        }

        @Override
        public ConnectionConsumer createConnectionConsumer(Destination destination, String messageSelector,
                ServerSessionPool sessionPool, int maxMessages) {
            return null;
        }

        @Override
        public ConnectionConsumer createSharedConnectionConsumer(Topic topic, String subscriptionName,
                String messageSelector, ServerSessionPool sessionPool, int maxMessages) {
            return null;
        }

        @Override
        public ConnectionConsumer createDurableConnectionConsumer(Topic topic, String subscriptionName,
                String messageSelector, ServerSessionPool sessionPool, int maxMessages) {
            return null;
        }

        @Override
        public ConnectionConsumer createSharedDurableConnectionConsumer(Topic topic, String subscriptionName,
                String messageSelector, ServerSessionPool sessionPool, int maxMessages) {
            return null;
        }

        void fireException(JMSException exception) throws JMSException {
            exceptionListener.onException(exception);
        }
    }

    private static final class FakeXAConnection extends FakeConnection implements XAConnection {
        private final List<FakeXASession> createdSessions = new ArrayList<>();
        private String lastUsername;
        private String lastPassword;

        @Override
        public XASession createXASession() {
            FakeXASession created = new FakeXASession("session-" + createdSessions.size());
            createdSessions.add(created);
            return created;
        }
    }

    private static class FakeSession implements Session {
        private final FakeMessageProducer producer = new FakeMessageProducer();
        private final FakeMessageConsumer consumer = new FakeMessageConsumer();
        private final FakeQueueBrowser browser = new FakeQueueBrowser();
        private final FakeTemporaryQueue temporaryQueue = new FakeTemporaryQueue("tmp-queue");
        private final FakeTemporaryTopic temporaryTopic = new FakeTemporaryTopic("tmp-topic");
        private MessageListener listener;
        private boolean transacted;
        private int acknowledgeMode = Session.AUTO_ACKNOWLEDGE;
        private int closeCalls;

        @Override
        public BytesMessage createBytesMessage() {
            return null;
        }

        @Override
        public MapMessage createMapMessage() {
            return null;
        }

        @Override
        public Message createMessage() {
            return message();
        }

        @Override
        public ObjectMessage createObjectMessage() {
            return null;
        }

        @Override
        public ObjectMessage createObjectMessage(Serializable object) {
            return null;
        }

        @Override
        public StreamMessage createStreamMessage() {
            return null;
        }

        @Override
        public TextMessage createTextMessage() {
            return null;
        }

        @Override
        public TextMessage createTextMessage(String text) {
            return null;
        }

        @Override
        public boolean getTransacted() {
            return transacted;
        }

        @Override
        public int getAcknowledgeMode() {
            return acknowledgeMode;
        }

        @Override
        public void commit() {
        }

        @Override
        public void rollback() {
        }

        @Override
        public void close() {
            closeCalls++;
        }

        @Override
        public void recover() {
        }

        @Override
        public MessageListener getMessageListener() {
            return listener;
        }

        @Override
        public void setMessageListener(MessageListener listener) {
            this.listener = listener;
        }

        @Override
        public void run() {
        }

        @Override
        public MessageProducer createProducer(Destination destination) {
            producer.destination = destination;
            return producer;
        }

        @Override
        public MessageConsumer createConsumer(Destination destination) {
            consumer.destination = destination;
            return consumer;
        }

        @Override
        public MessageConsumer createConsumer(Destination destination, String messageSelector) {
            consumer.destination = destination;
            consumer.messageSelector = messageSelector;
            return consumer;
        }

        @Override
        public MessageConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) {
            consumer.destination = destination;
            consumer.messageSelector = messageSelector;
            consumer.noLocal = noLocal;
            return consumer;
        }

        @Override
        public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) {
            return consumer;
        }

        @Override
        public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName,
                String messageSelector) {
            consumer.messageSelector = messageSelector;
            return consumer;
        }

        @Override
        public jakarta.jms.Queue createQueue(String queueName) {
            return new SimpleQueue(queueName);
        }

        @Override
        public Topic createTopic(String topicName) {
            return new SimpleTopic(topicName);
        }

        @Override
        public TopicSubscriber createDurableSubscriber(Topic topic, String name) {
            return null;
        }

        @Override
        public TopicSubscriber createDurableSubscriber(Topic topic, String name, String messageSelector,
                boolean noLocal) {
            return null;
        }

        @Override
        public MessageConsumer createDurableConsumer(Topic topic, String name) {
            return consumer;
        }

        @Override
        public MessageConsumer createDurableConsumer(Topic topic, String name, String messageSelector,
                boolean noLocal) {
            consumer.messageSelector = messageSelector;
            consumer.noLocal = noLocal;
            return consumer;
        }

        @Override
        public MessageConsumer createSharedDurableConsumer(Topic topic, String name) {
            return consumer;
        }

        @Override
        public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) {
            consumer.messageSelector = messageSelector;
            return consumer;
        }

        @Override
        public QueueBrowser createBrowser(jakarta.jms.Queue queue) {
            browser.queue = queue;
            return browser;
        }

        @Override
        public QueueBrowser createBrowser(jakarta.jms.Queue queue, String messageSelector) {
            browser.queue = queue;
            browser.messageSelector = messageSelector;
            return browser;
        }

        @Override
        public TemporaryQueue createTemporaryQueue() {
            return temporaryQueue;
        }

        @Override
        public TemporaryTopic createTemporaryTopic() {
            return temporaryTopic;
        }

        @Override
        public void unsubscribe(String name) {
        }
    }

    private static final class FakeXASession extends FakeSession implements XASession {
        private final FakeXAResource xaResource;

        private FakeXASession(String id) {
            this.xaResource = new FakeXAResource(id);
        }

        @Override
        public Session getSession() {
            return this;
        }

        @Override
        public XAResource getXAResource() {
            return xaResource;
        }

        @Override
        public boolean getTransacted() {
            return true;
        }
    }

    private static final class FakeMessageProducer implements MessageProducer {
        private Destination destination;
        private Message lastMessage;
        private CompletionListener completionListener;
        private int closeCalls;
        private boolean disableMessageId;
        private boolean disableMessageTimestamp;
        private int deliveryMode;
        private int priority;
        private long timeToLive;
        private long deliveryDelay;

        @Override
        public void setDisableMessageID(boolean value) {
            disableMessageId = value;
        }

        @Override
        public boolean getDisableMessageID() {
            return disableMessageId;
        }

        @Override
        public void setDisableMessageTimestamp(boolean value) {
            disableMessageTimestamp = value;
        }

        @Override
        public boolean getDisableMessageTimestamp() {
            return disableMessageTimestamp;
        }

        @Override
        public void setDeliveryMode(int deliveryMode) {
            this.deliveryMode = deliveryMode;
        }

        @Override
        public int getDeliveryMode() {
            return deliveryMode;
        }

        @Override
        public void setPriority(int defaultPriority) {
            this.priority = defaultPriority;
        }

        @Override
        public int getPriority() {
            return priority;
        }

        @Override
        public void setTimeToLive(long timeToLive) {
            this.timeToLive = timeToLive;
        }

        @Override
        public long getTimeToLive() {
            return timeToLive;
        }

        @Override
        public void setDeliveryDelay(long deliveryDelay) {
            this.deliveryDelay = deliveryDelay;
        }

        @Override
        public long getDeliveryDelay() {
            return deliveryDelay;
        }

        @Override
        public Destination getDestination() {
            return destination;
        }

        @Override
        public void close() {
            closeCalls++;
        }

        @Override
        public void send(Message message) {
            lastMessage = message;
        }

        @Override
        public void send(Message message, int deliveryMode, int priority, long timeToLive) {
            this.deliveryMode = deliveryMode;
            this.priority = priority;
            this.timeToLive = timeToLive;
            lastMessage = message;
        }

        @Override
        public void send(Destination destination, Message message) {
            this.destination = destination;
            lastMessage = message;
        }

        @Override
        public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive) {
            this.destination = destination;
            this.deliveryMode = deliveryMode;
            this.priority = priority;
            this.timeToLive = timeToLive;
            lastMessage = message;
        }

        @Override
        public void send(Message message, CompletionListener completionListener) {
            lastMessage = message;
            this.completionListener = completionListener;
        }

        @Override
        public void send(Message message, int deliveryMode, int priority, long timeToLive,
                CompletionListener completionListener) {
            send(message, deliveryMode, priority, timeToLive);
            this.completionListener = completionListener;
        }

        @Override
        public void send(Destination destination, Message message, CompletionListener completionListener) {
            send(destination, message);
            this.completionListener = completionListener;
        }

        @Override
        public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive,
                CompletionListener completionListener) {
            send(destination, message, deliveryMode, priority, timeToLive);
            this.completionListener = completionListener;
        }

        void fireCompletion() {
            completionListener.onCompletion(lastMessage);
        }

        void fireException(Exception exception) {
            completionListener.onException(lastMessage, exception);
        }
    }

    private static final class FakeMessageConsumer implements MessageConsumer {
        private Destination destination;
        private String messageSelector;
        private boolean noLocal;
        private MessageListener listener;
        private int closeCalls;

        @Override
        public String getMessageSelector() {
            return messageSelector;
        }

        @Override
        public MessageListener getMessageListener() {
            return listener;
        }

        @Override
        public void setMessageListener(MessageListener listener) {
            this.listener = listener;
        }

        @Override
        public Message receive() {
            return message();
        }

        @Override
        public Message receive(long timeout) {
            return message();
        }

        @Override
        public Message receiveNoWait() {
            return message();
        }

        @Override
        public void close() {
            closeCalls++;
        }

        void fireMessage() {
            listener.onMessage(message());
        }
    }

    private static final class FakeQueueBrowser implements QueueBrowser {
        private jakarta.jms.Queue queue;
        private String messageSelector;

        @Override
        public jakarta.jms.Queue getQueue() {
            return queue;
        }

        @Override
        public String getMessageSelector() {
            return messageSelector;
        }

        @Override
        public Enumeration<?> getEnumeration() {
            return Collections.emptyEnumeration();
        }

        @Override
        public void close() {
        }
    }

    private static final class FakeTemporaryQueue extends SimpleQueue implements TemporaryQueue {
        private boolean deleted;

        private FakeTemporaryQueue(String name) {
            super(name);
        }

        @Override
        public void delete() {
            deleted = true;
        }
    }

    private static final class FakeTemporaryTopic extends SimpleTopic implements TemporaryTopic {
        private boolean deleted;

        private FakeTemporaryTopic(String name) {
            super(name);
        }

        @Override
        public void delete() {
            deleted = true;
        }
    }

    private static class FakeJMSContext implements JMSContext {
        private final FakeJMSProducer producer = new FakeJMSProducer();
        private final FakeJMSConsumer consumer = new FakeJMSConsumer();
        private final FakeQueueBrowser browser = new FakeQueueBrowser();
        private final FakeTemporaryQueue temporaryQueue = new FakeTemporaryQueue("ctx-tmp-queue");
        private final FakeTemporaryTopic temporaryTopic = new FakeTemporaryTopic("ctx-tmp-topic");
        private ExceptionListener exceptionListener;
        private boolean autoStart;
        private int sessionMode = AUTO_ACKNOWLEDGE;
        private String clientId;
        private int closeCalls;

        @Override
        public JMSContext createContext(int sessionMode) {
            this.sessionMode = sessionMode;
            return this;
        }

        @Override
        public JMSProducer createProducer() {
            return producer;
        }

        @Override
        public String getClientID() {
            return clientId;
        }

        @Override
        public void setClientID(String clientID) {
            this.clientId = clientID;
        }

        @Override
        public ConnectionMetaData getMetaData() {
            return null;
        }

        @Override
        public ExceptionListener getExceptionListener() {
            return exceptionListener;
        }

        @Override
        public void setExceptionListener(ExceptionListener listener) {
            this.exceptionListener = listener;
        }

        @Override
        public void start() {
            autoStart = true;
        }

        @Override
        public void stop() {
            autoStart = false;
        }

        @Override
        public void setAutoStart(boolean autoStart) {
            this.autoStart = autoStart;
        }

        @Override
        public boolean getAutoStart() {
            return autoStart;
        }

        @Override
        public void close() {
            closeCalls++;
        }

        @Override
        public BytesMessage createBytesMessage() {
            return null;
        }

        @Override
        public MapMessage createMapMessage() {
            return null;
        }

        @Override
        public Message createMessage() {
            return message();
        }

        @Override
        public ObjectMessage createObjectMessage() {
            return null;
        }

        @Override
        public ObjectMessage createObjectMessage(Serializable object) {
            return null;
        }

        @Override
        public StreamMessage createStreamMessage() {
            return null;
        }

        @Override
        public TextMessage createTextMessage() {
            return null;
        }

        @Override
        public TextMessage createTextMessage(String text) {
            return null;
        }

        @Override
        public boolean getTransacted() {
            return sessionMode == SESSION_TRANSACTED;
        }

        @Override
        public int getSessionMode() {
            return sessionMode;
        }

        @Override
        public void commit() {
        }

        @Override
        public void rollback() {
        }

        @Override
        public void recover() {
        }

        @Override
        public JMSConsumer createConsumer(Destination destination) {
            consumer.destination = destination;
            return consumer;
        }

        @Override
        public JMSConsumer createConsumer(Destination destination, String messageSelector) {
            consumer.destination = destination;
            consumer.messageSelector = messageSelector;
            return consumer;
        }

        @Override
        public JMSConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) {
            consumer.destination = destination;
            consumer.messageSelector = messageSelector;
            consumer.noLocal = noLocal;
            return consumer;
        }

        @Override
        public jakarta.jms.Queue createQueue(String queueName) {
            return new SimpleQueue(queueName);
        }

        @Override
        public Topic createTopic(String topicName) {
            return new SimpleTopic(topicName);
        }

        @Override
        public JMSConsumer createDurableConsumer(Topic topic, String name) {
            return consumer;
        }

        @Override
        public JMSConsumer createDurableConsumer(Topic topic, String name, String messageSelector, boolean noLocal) {
            consumer.messageSelector = messageSelector;
            consumer.noLocal = noLocal;
            return consumer;
        }

        @Override
        public JMSConsumer createSharedDurableConsumer(Topic topic, String name) {
            return consumer;
        }

        @Override
        public JMSConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) {
            consumer.messageSelector = messageSelector;
            return consumer;
        }

        @Override
        public JMSConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) {
            return consumer;
        }

        @Override
        public JMSConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) {
            consumer.messageSelector = messageSelector;
            return consumer;
        }

        @Override
        public QueueBrowser createBrowser(jakarta.jms.Queue queue) {
            browser.queue = queue;
            return browser;
        }

        @Override
        public QueueBrowser createBrowser(jakarta.jms.Queue queue, String messageSelector) {
            browser.queue = queue;
            browser.messageSelector = messageSelector;
            return browser;
        }

        @Override
        public TemporaryQueue createTemporaryQueue() {
            return temporaryQueue;
        }

        @Override
        public TemporaryTopic createTemporaryTopic() {
            return temporaryTopic;
        }

        @Override
        public void unsubscribe(String name) {
        }

        @Override
        public void acknowledge() {
        }
    }

    private static final class FakeXAJMSContext extends FakeJMSContext implements XAJMSContext {
        private final FakeXAResource xaResource = new FakeXAResource("context-xa");
        private final FakeJMSContext nestedContext = new FakeJMSContext();
        private String lastUsername;
        private String lastPassword;

        @Override
        public JMSContext getContext() {
            return nestedContext;
        }

        @Override
        public XAResource getXAResource() {
            return xaResource;
        }

        @Override
        public boolean getTransacted() {
            return true;
        }
    }

    private static final class FakeJMSProducer implements JMSProducer {
        private Destination destination;
        private Message lastMessage;
        private CompletionListener asyncListener;
        private boolean disableMessageId;
        private boolean disableMessageTimestamp;
        private int deliveryMode;
        private int priority;
        private long timeToLive;
        private long deliveryDelay;
        private final Map<String, Object> properties = new HashMap<>();
        private byte[] correlationIdBytes;
        private String correlationId;
        private String type;
        private Destination replyTo;

        @Override
        public JMSProducer send(Destination destination, Message message) {
            this.destination = destination;
            this.lastMessage = message;
            return this;
        }

        @Override
        public JMSProducer send(Destination destination, String body) {
            this.destination = destination;
            this.lastMessage = message();
            return this;
        }

        @Override
        public JMSProducer send(Destination destination, Map<String, Object> body) {
            this.destination = destination;
            this.lastMessage = message();
            return this;
        }

        @Override
        public JMSProducer send(Destination destination, byte[] body) {
            this.destination = destination;
            this.lastMessage = message();
            return this;
        }

        @Override
        public JMSProducer send(Destination destination, Serializable body) {
            this.destination = destination;
            this.lastMessage = message();
            return this;
        }

        @Override
        public JMSProducer setDisableMessageID(boolean value) {
            disableMessageId = value;
            return this;
        }

        @Override
        public boolean getDisableMessageID() {
            return disableMessageId;
        }

        @Override
        public JMSProducer setDisableMessageTimestamp(boolean value) {
            disableMessageTimestamp = value;
            return this;
        }

        @Override
        public boolean getDisableMessageTimestamp() {
            return disableMessageTimestamp;
        }

        @Override
        public JMSProducer setDeliveryMode(int deliveryMode) {
            this.deliveryMode = deliveryMode;
            return this;
        }

        @Override
        public int getDeliveryMode() {
            return deliveryMode;
        }

        @Override
        public JMSProducer setPriority(int priority) {
            this.priority = priority;
            return this;
        }

        @Override
        public int getPriority() {
            return priority;
        }

        @Override
        public JMSProducer setTimeToLive(long timeToLive) {
            this.timeToLive = timeToLive;
            return this;
        }

        @Override
        public long getTimeToLive() {
            return timeToLive;
        }

        @Override
        public JMSProducer setDeliveryDelay(long deliveryDelay) {
            this.deliveryDelay = deliveryDelay;
            return this;
        }

        @Override
        public long getDeliveryDelay() {
            return deliveryDelay;
        }

        @Override
        public JMSProducer setAsync(CompletionListener completionListener) {
            this.asyncListener = completionListener;
            return this;
        }

        @Override
        public CompletionListener getAsync() {
            return asyncListener;
        }

        @Override
        public JMSProducer setProperty(String name, boolean value) {
            properties.put(name, value);
            return this;
        }

        @Override
        public JMSProducer setProperty(String name, byte value) {
            properties.put(name, value);
            return this;
        }

        @Override
        public JMSProducer setProperty(String name, short value) {
            properties.put(name, value);
            return this;
        }

        @Override
        public JMSProducer setProperty(String name, int value) {
            properties.put(name, value);
            return this;
        }

        @Override
        public JMSProducer setProperty(String name, long value) {
            properties.put(name, value);
            return this;
        }

        @Override
        public JMSProducer setProperty(String name, float value) {
            properties.put(name, value);
            return this;
        }

        @Override
        public JMSProducer setProperty(String name, double value) {
            properties.put(name, value);
            return this;
        }

        @Override
        public JMSProducer setProperty(String name, String value) {
            properties.put(name, value);
            return this;
        }

        @Override
        public JMSProducer setProperty(String name, Object value) {
            properties.put(name, value);
            return this;
        }

        @Override
        public JMSProducer clearProperties() {
            properties.clear();
            return this;
        }

        @Override
        public boolean propertyExists(String name) {
            return properties.containsKey(name);
        }

        @Override
        public boolean getBooleanProperty(String name) {
            return (boolean) properties.get(name);
        }

        @Override
        public byte getByteProperty(String name) {
            return (byte) properties.get(name);
        }

        @Override
        public short getShortProperty(String name) {
            return (short) properties.get(name);
        }

        @Override
        public int getIntProperty(String name) {
            return (int) properties.get(name);
        }

        @Override
        public long getLongProperty(String name) {
            return (long) properties.get(name);
        }

        @Override
        public float getFloatProperty(String name) {
            return (float) properties.get(name);
        }

        @Override
        public double getDoubleProperty(String name) {
            return (double) properties.get(name);
        }

        @Override
        public String getStringProperty(String name) {
            return (String) properties.get(name);
        }

        @Override
        public Object getObjectProperty(String name) {
            return properties.get(name);
        }

        @Override
        public Set<String> getPropertyNames() {
            return new HashSet<>(properties.keySet());
        }

        @Override
        public JMSProducer setJMSCorrelationIDAsBytes(byte[] correlationID) {
            correlationIdBytes = correlationID;
            return this;
        }

        @Override
        public byte[] getJMSCorrelationIDAsBytes() {
            return correlationIdBytes;
        }

        @Override
        public JMSProducer setJMSCorrelationID(String correlationID) {
            correlationId = correlationID;
            return this;
        }

        @Override
        public String getJMSCorrelationID() {
            return correlationId;
        }

        @Override
        public JMSProducer setJMSType(String type) {
            this.type = type;
            return this;
        }

        @Override
        public String getJMSType() {
            return type;
        }

        @Override
        public JMSProducer setJMSReplyTo(Destination replyTo) {
            this.replyTo = replyTo;
            return this;
        }

        @Override
        public Destination getJMSReplyTo() {
            return replyTo;
        }
    }

    private static final class FakeJMSConsumer implements JMSConsumer {
        private Destination destination;
        private String messageSelector;
        private boolean noLocal;
        private MessageListener listener;
        private int closeCalls;

        @Override
        public String getMessageSelector() {
            return messageSelector;
        }

        @Override
        public MessageListener getMessageListener() {
            return listener;
        }

        @Override
        public void setMessageListener(MessageListener listener) {
            this.listener = listener;
        }

        @Override
        public Message receive() {
            return message();
        }

        @Override
        public Message receive(long timeout) {
            return message();
        }

        @Override
        public Message receiveNoWait() {
            return message();
        }

        @Override
        public void close() {
            closeCalls++;
        }

        @Override
        public <T> T receiveBody(Class<T> c) {
            return null;
        }

        @Override
        public <T> T receiveBody(Class<T> c, long timeout) {
            return null;
        }

        @Override
        public <T> T receiveBodyNoWait(Class<T> c) {
            return null;
        }
    }

    private static final class FakeXAResource implements XAResource {
        private final String id;
        private int timeout;

        private FakeXAResource(String id) {
            this.id = id;
        }

        @Override
        public void commit(Xid xid, boolean onePhase) {
        }

        @Override
        public void end(Xid xid, int flags) {
        }

        @Override
        public void forget(Xid xid) {
        }

        @Override
        public int getTransactionTimeout() {
            return timeout;
        }

        @Override
        public boolean isSameRM(XAResource xaResource) {
            return xaResource == this;
        }

        @Override
        public int prepare(Xid xid) {
            return XA_OK;
        }

        @Override
        public Xid[] recover(int flag) {
            return new Xid[0];
        }

        @Override
        public void rollback(Xid xid) {
        }

        @Override
        public boolean setTransactionTimeout(int seconds) {
            timeout = seconds;
            return true;
        }

        @Override
        public void start(Xid xid, int flags) {
        }

        @Override
        public String toString() {
            return "FakeXAResource[" + id + ']';
        }
    }

    private static class SimpleQueue implements jakarta.jms.Queue {
        private final String name;

        private SimpleQueue(String name) {
            this.name = name;
        }

        @Override
        public String getQueueName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    @Test
    void sharedMonitoringFilterObservesJmsCallsAcrossTheGraph() throws JMSException {
        io.github.rrobetti.japiproxy.core.examples.MonitoringFilter monitoring =
                new io.github.rrobetti.japiproxy.core.examples.MonitoringFilter();
        FakeConnectionFactory delegate = new FakeConnectionFactory();

        ConnectionFactory wrapped = JmsProxy.wrap(delegate, "orders", monitoring);
        Connection connection = wrapped.createConnection();
        Session session = connection.createSession();
        session.createProducer(TEST_DESTINATION);

        assertEquals(1, monitoring.successCount(ConnectionFactory.class, "createConnection"));
        assertEquals(1, monitoring.successCount(Connection.class, "createSession"));
        assertEquals(1, monitoring.successCount(Session.class, "createProducer"));
    }

    private static class SimpleTopic implements Topic {
        private final String name;

        private SimpleTopic(String name) {
            this.name = name;
        }

        @Override
        public String getTopicName() {
            return name;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
