package io.github.rrobetti.japiproxy.jdbc;

import io.github.rrobetti.japiproxy.core.ArgumentAdapter;
import io.github.rrobetti.japiproxy.core.InterfaceProxy;
import io.github.rrobetti.japiproxy.core.InvocationContext;
import io.github.rrobetti.japiproxy.core.InvocationFilter;
import io.github.rrobetti.japiproxy.core.ProxyContext;
import io.github.rrobetti.japiproxy.core.ReturnValueAdapter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import javax.sql.DataSource;
import javax.sql.PooledConnection;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;

/**
 * Factory methods for recursively proxying standard JDBC and XA interfaces.
 * Only standard {@code java.sql}, {@code javax.sql}, and {@code javax.transaction.xa}
 * interfaces are proxied. Casting returned proxies to vendor-specific implementation classes
 * will fail, and calling vendor delegates directly through {@code unwrap()} bypasses interception.
 */
public final class JdbcProxy {
    private JdbcProxy() {
    }

    /**
     * Wraps a data source using default recursive options.
     *
     * @param delegate the data source to wrap
     * @param resourceName the logical resource name
     * @param filters the invocation filters to apply
     * @return the proxied data source
     */
    public static DataSource wrap(DataSource delegate, String resourceName, InvocationFilter... filters) {
        return wrap(delegate, resourceName, JdbcProxyOptions.builder().build(), filters);
    }

    /**
     * Wraps a data source using default recursive options.
     *
     * @param delegate the data source to wrap
     * @param resourceName the logical resource name
     * @param filters the invocation filters to apply
     * @return the proxied data source
     */
    public static DataSource wrap(DataSource delegate, String resourceName, List<InvocationFilter> filters) {
        return wrap(delegate, resourceName, JdbcProxyOptions.builder().build(), filters);
    }

    /**
     * Wraps a data source.
     *
     * @param delegate the data source to wrap
     * @param resourceName the logical resource name
     * @param options recursive wrapping options
     * @param filters the invocation filters to apply
     * @return the proxied data source
     */
    public static DataSource wrap(DataSource delegate, String resourceName, JdbcProxyOptions options,
            InvocationFilter... filters) {
        return wrap(delegate, resourceName, options, Arrays.asList(filters));
    }

    /**
     * Wraps a data source.
     *
     * @param delegate the data source to wrap
     * @param resourceName the logical resource name
     * @param options recursive wrapping options
     * @param filters the invocation filters to apply
     * @return the proxied data source
     */
    public static DataSource wrap(DataSource delegate, String resourceName, JdbcProxyOptions options,
            List<InvocationFilter> filters) {
        JdbcRuntime runtime = new JdbcRuntime(resourceName, filters, options);
        return InterfaceProxy.builder(DataSource.class, delegate)
                .proxyContext(runtime.proxyContext())
                .returnValueAdapter(runtime.returnValueAdapter())
                .argumentAdapter(runtime.argumentAdapter())
                .build();
    }

    /**
     * Wraps an XA data source using default recursive options.
     *
     * @param delegate the XA data source to wrap
     * @param resourceName the logical resource name
     * @param filters the invocation filters to apply
     * @return the proxied XA data source
     */
    public static XADataSource wrapXa(XADataSource delegate, String resourceName, InvocationFilter... filters) {
        return wrapXa(delegate, resourceName, JdbcProxyOptions.builder().build(), filters);
    }

    /**
     * Wraps an XA data source using default recursive options.
     *
     * @param delegate the XA data source to wrap
     * @param resourceName the logical resource name
     * @param filters the invocation filters to apply
     * @return the proxied XA data source
     */
    public static XADataSource wrapXa(XADataSource delegate, String resourceName, List<InvocationFilter> filters) {
        return wrapXa(delegate, resourceName, JdbcProxyOptions.builder().build(), filters);
    }

    /**
     * Wraps an XA data source.
     *
     * @param delegate the XA data source to wrap
     * @param resourceName the logical resource name
     * @param options recursive wrapping options
     * @param filters the invocation filters to apply
     * @return the proxied XA data source
     */
    public static XADataSource wrapXa(XADataSource delegate, String resourceName, JdbcProxyOptions options,
            InvocationFilter... filters) {
        return wrapXa(delegate, resourceName, options, Arrays.asList(filters));
    }

    /**
     * Wraps an XA data source.
     *
     * @param delegate the XA data source to wrap
     * @param resourceName the logical resource name
     * @param options recursive wrapping options
     * @param filters the invocation filters to apply
     * @return the proxied XA data source
     */
    public static XADataSource wrapXa(XADataSource delegate, String resourceName, JdbcProxyOptions options,
            List<InvocationFilter> filters) {
        JdbcRuntime runtime = new JdbcRuntime(resourceName, filters, options);
        return InterfaceProxy.builder(XADataSource.class, delegate)
                .proxyContext(runtime.proxyContext())
                .returnValueAdapter(runtime.returnValueAdapter())
                .argumentAdapter(runtime.argumentAdapter())
                .build();
    }

    private static final class JdbcRuntime {
        private final ProxyContext proxyContext;
        private final JdbcReturnValueAdapter returnValueAdapter;
        private final ArgumentAdapter argumentAdapter = JdbcProxy::adaptArguments;

        private JdbcRuntime(String resourceName, List<InvocationFilter> filters, JdbcProxyOptions options) {
            this.proxyContext = new ProxyContext(resourceName, filters);
            this.returnValueAdapter = new JdbcReturnValueAdapter(options);
        }

        private ProxyContext proxyContext() {
            return proxyContext;
        }

        private ReturnValueAdapter returnValueAdapter() {
            return returnValueAdapter;
        }

        private ArgumentAdapter argumentAdapter() {
            return argumentAdapter;
        }
    }

    private static Object[] adaptArguments(InvocationContext invocation, Object[] arguments, ProxyContext proxyContext) {
        if (arguments == null || arguments.length == 0) {
            return arguments;
        }
        if (invocation.interfaceType() == XAResource.class && "isSameRM".equals(invocation.method().getName())) {
            Object[] adapted = arguments.clone();
            adapted[0] = proxyContext.unwrap(arguments[0]);
            return adapted;
        }
        return arguments;
    }

    private static final class JdbcReturnValueAdapter implements ReturnValueAdapter {
        private final JdbcProxyOptions options;

        private JdbcReturnValueAdapter(JdbcProxyOptions options) {
            this.options = options;
        }

        @Override
        public Object adapt(InvocationContext invocation, Object returnedValue, ProxyContext proxyContext) {
            if (returnedValue == null || "unwrap".equals(invocation.method().getName())) {
                return returnedValue;
            }
            Object parent = invocation.proxy();
            if (returnedValue instanceof XAConnection xaConnection) {
                return proxyContext.wrap(xaConnection, XAConnection.class, parent, this, JdbcProxy::adaptArguments,
                        PooledConnection.class);
            }
            if (returnedValue instanceof XAResource xaResource) {
                return proxyContext.wrap(xaResource, XAResource.class, parent, this, JdbcProxy::adaptArguments);
            }
            if (returnedValue instanceof CallableStatement callableStatement && options.statements()) {
                return proxyContext.wrap(callableStatement, CallableStatement.class, parent, this,
                        JdbcProxy::adaptArguments, PreparedStatement.class, Statement.class);
            }
            if (returnedValue instanceof PreparedStatement preparedStatement && options.statements()) {
                return proxyContext.wrap(preparedStatement, PreparedStatement.class, parent, this,
                        JdbcProxy::adaptArguments, Statement.class);
            }
            if (returnedValue instanceof Statement statement && options.statements()) {
                return proxyContext.wrap(statement, Statement.class, parent, this, JdbcProxy::adaptArguments);
            }
            if (returnedValue instanceof ResultSet resultSet && options.resultSets()) {
                return proxyContext.wrap(resultSet, ResultSet.class, parent, this, JdbcProxy::adaptArguments);
            }
            if (returnedValue instanceof DatabaseMetaData metaData && options.connections()) {
                return proxyContext.wrap(metaData, DatabaseMetaData.class, parent, this, JdbcProxy::adaptArguments);
            }
            if (returnedValue instanceof Connection connection && options.connections()) {
                return proxyContext.wrap(connection, Connection.class, parent, this, JdbcProxy::adaptArguments);
            }
            return returnedValue;
        }
    }
}
