package io.github.rrobetti.japiproxy.jdbc;

/**
 * Controls recursive JDBC proxy depth.
 */
public final class JdbcProxyOptions {
    private final boolean connections;
    private final boolean statements;
    private final boolean resultSets;

    private JdbcProxyOptions(Builder builder) {
        this.connections = builder.connections;
        this.statements = builder.statements;
        this.resultSets = builder.resultSets;
    }

    /**
     * Creates a new options builder.
     *
     * @return the builder
     */
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns whether connections are wrapped.
     *
     * @return whether connections are wrapped
     */
    public boolean connections() {
        return connections;
    }

    /**
     * Returns whether statements are wrapped.
     *
     * @return whether statements are wrapped
     */
    public boolean statements() {
        return statements;
    }

    /**
     * Returns whether result sets are wrapped.
     *
     * @return whether result sets are wrapped
     */
    public boolean resultSets() {
        return resultSets;
    }

    /**
     * Builder for {@link JdbcProxyOptions}.
     */
    public static final class Builder {
        private boolean connections = true;
        private boolean statements = true;
        private boolean resultSets = true;

        private Builder() {
        }

        /**
         * Sets whether connections are wrapped.
         *
         * @param connections whether connections are wrapped
         * @return this builder
         */
        public Builder connections(boolean connections) {
            this.connections = connections;
            return this;
        }

        /**
         * Sets whether statements are wrapped.
         *
         * @param statements whether statements are wrapped
         * @return this builder
         */
        public Builder statements(boolean statements) {
            this.statements = statements;
            return this;
        }

        /**
         * Sets whether result sets are wrapped.
         *
         * @param resultSets whether result sets are wrapped
         * @return this builder
         */
        public Builder resultSets(boolean resultSets) {
            this.resultSets = resultSets;
            return this;
        }

        /**
         * Builds the options.
         *
         * @return the options instance
         */
        public JdbcProxyOptions build() {
            return new JdbcProxyOptions(this);
        }
    }
}
