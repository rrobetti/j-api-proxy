package io.github.rrobetti.japiproxy.jms;

/**
 * Options controlling how deeply Jakarta JMS object graphs are proxied.
 */
public final class JmsProxyOptions {
    private final boolean connections;
    private final boolean sessions;
    private final boolean consumersProducers;

    private JmsProxyOptions(Builder builder) {
        this.connections = builder.connections;
        this.sessions = builder.sessions;
        this.consumersProducers = builder.consumersProducers;
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean connections() {
        return connections;
    }

    /**
     * Enables wrapping of {@code Session}/{@code XASession} and other session-scoped artifacts such as
     * {@code QueueBrowser}, {@code TemporaryQueue}, and {@code TemporaryTopic}, including when those
     * artifacts are created through a {@code JMSContext}.
     *
     * @return whether session-scoped objects should be wrapped
     */
    public boolean sessions() {
        return sessions;
    }

    public boolean consumersProducers() {
        return consumersProducers;
    }

    public static final class Builder {
        private boolean connections = true;
        private boolean sessions = true;
        private boolean consumersProducers = true;

        private Builder() {
        }

        public Builder connections(boolean connections) {
            this.connections = connections;
            return this;
        }

        /**
         * Controls wrapping of {@code Session}/{@code XASession} plus session-scoped objects created
         * from either sessions or {@code JMSContext} handles.
         *
         * @param sessions whether session-scoped objects should be wrapped
         * @return this builder
         */
        public Builder sessions(boolean sessions) {
            this.sessions = sessions;
            return this;
        }

        public Builder consumersProducers(boolean consumersProducers) {
            this.consumersProducers = consumersProducers;
            return this;
        }

        public JmsProxyOptions build() {
            return new JmsProxyOptions(this);
        }
    }
}
