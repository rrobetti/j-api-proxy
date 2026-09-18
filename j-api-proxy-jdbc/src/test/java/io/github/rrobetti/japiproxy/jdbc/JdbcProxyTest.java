package io.github.rrobetti.japiproxy.jdbc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.github.rrobetti.japiproxy.core.InterfaceProxy;
import io.github.rrobetti.japiproxy.core.InvocationFilter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.ConnectionEventListener;
import javax.sql.DataSource;
import javax.sql.StatementEventListener;
import javax.sql.XAConnection;
import javax.sql.XADataSource;
import javax.transaction.xa.XAResource;
import org.junit.jupiter.api.Test;

class JdbcProxyTest {
    @Test
    void wrapsFactoryToResultSetGraphAndInvokesFiltersAtEachLevel() throws Exception {
        FakeDataSource delegate = new FakeDataSource();
        List<String> events = new ArrayList<>();
        InvocationFilter filter = (invocation, chain) -> {
            events.add(invocation.interfaceType().getSimpleName() + "." + invocation.method().getName());
            return chain.proceed(invocation);
        };

        DataSource dataSource = JdbcProxy.wrap(delegate, "orders", filter);
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select 1");
        resultSet.next();

        assertTrue(events.contains("DataSource.getConnection"));
        assertTrue(events.contains("Connection.createStatement"));
        assertTrue(events.contains("Statement.executeQuery"));
        assertTrue(events.contains("ResultSet.next"));
        assertTrue(connection instanceof Connection);
        assertTrue(statement instanceof Statement);
        assertTrue(resultSet instanceof ResultSet);
    }

    @Test
    void wrapsStatementsPreparedStatementsAndCallableStatements() throws Exception {
        FakeDataSource delegate = new FakeDataSource();
        DataSource dataSource = JdbcProxy.wrap(delegate, "orders");
        Connection connection = dataSource.getConnection();

        Statement statement = connection.createStatement();
        PreparedStatement preparedStatement = connection.prepareStatement("select ?");
        CallableStatement callableStatement = connection.prepareCall("{call demo()}");

        assertInstanceOf(Statement.class, statement);
        assertInstanceOf(PreparedStatement.class, preparedStatement);
        assertInstanceOf(Statement.class, preparedStatement);
        assertInstanceOf(CallableStatement.class, callableStatement);
        assertInstanceOf(PreparedStatement.class, callableStatement);
        assertInstanceOf(Statement.class, callableStatement);
        assertEquals("select ?", preparedStatement.executeQuery().getString(1));
        assertEquals("{call demo()}", callableStatement.executeQuery().getString(1));
    }

    @Test
    void wrapsResultSetsAndMetadata() throws Exception {
        FakeDataSource delegate = new FakeDataSource();
        DataSource dataSource = JdbcProxy.wrap(delegate, "orders");
        Connection connection = dataSource.getConnection();
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet generatedKeys = connection.createStatement().getGeneratedKeys();

        assertEquals("jdbc:fake", metaData.getURL());
        assertSame(delegate.openedConnections.get(0), InterfaceProxy.unwrap(connection));
        assertTrue(generatedKeys.next());
        assertEquals("generated", generatedKeys.getString(1));
    }

    @Test
    void wrapsXaFactoryConnectionAndResourceGraph() throws Exception {
        FakeXADataSource delegate = new FakeXADataSource();
        XADataSource xaDataSource = JdbcProxy.wrapXa(delegate, "xa");
        XAConnection xaConnection = xaDataSource.getXAConnection();
        Connection connection = xaConnection.getConnection();
        XAResource xaResource = xaConnection.getXAResource();

        assertTrue(xaConnection instanceof XAConnection);
        assertTrue(connection instanceof Connection);
        assertTrue(xaResource instanceof XAResource);
    }

    @Test
    void repeatedXaResourceAccessReturnsSameProxyInstance() throws Exception {
        FakeXADataSource delegate = new FakeXADataSource();
        XAConnection xaConnection = JdbcProxy.wrapXa(delegate, "xa").getXAConnection();

        XAResource first = xaConnection.getXAResource();
        XAResource second = xaConnection.getXAResource();

        assertSame(first, second);
    }

    @Test
    void multipleXaConnectionsYieldDistinctResourceProxies() throws Exception {
        FakeXADataSource delegate = new FakeXADataSource();
        XADataSource xaDataSource = JdbcProxy.wrapXa(delegate, "xa");

        XAResource first = xaDataSource.getXAConnection().getXAResource();
        XAResource second = xaDataSource.getXAConnection().getXAResource();

        assertNotSame(first, second);
    }

    @Test
    void credentialedConnectionsDelegateCredentials() throws Exception {
        FakeDataSource delegate = new FakeDataSource();
        DataSource dataSource = JdbcProxy.wrap(delegate, "orders");

        Connection connection = dataSource.getConnection("user-a", "secret-b");

        assertTrue(connection instanceof Connection);
        assertEquals("user-a", delegate.lastUser);
        assertEquals("secret-b", delegate.lastPassword);
    }

    @Test
    void listenersCloseAndWrapperMethodsPassThrough() throws Exception {
        FakeXADataSource xaDelegate = new FakeXADataSource();
        XAConnection xaConnection = JdbcProxy.wrapXa(xaDelegate, "xa").getXAConnection();
        ConnectionEventListener connectionEventListener = new ConnectionEventListener() {
            @Override
            public void connectionClosed(javax.sql.ConnectionEvent event) {
            }

            @Override
            public void connectionErrorOccurred(javax.sql.ConnectionEvent event) {
            }
        };
        StatementEventListener statementEventListener = new StatementEventListener() {
            @Override
            public void statementClosed(javax.sql.StatementEvent event) {
            }

            @Override
            public void statementErrorOccurred(javax.sql.StatementEvent event) {
            }
        };

        xaConnection.addConnectionEventListener(connectionEventListener);
        xaConnection.addStatementEventListener(statementEventListener);
        Connection connection = xaConnection.getConnection();
        Statement statement = connection.createStatement();
        FakeConnection rawConnection = (FakeConnection) connection.unwrap(FakeConnection.class);
        FakeStatement rawStatement = (FakeStatement) statement.unwrap(FakeStatement.class);

        assertSame(rawConnection, InterfaceProxy.unwrap(connection));
        assertSame(rawStatement, InterfaceProxy.unwrap(statement));
        assertTrue(connection.isWrapperFor(FakeConnection.class));
        assertTrue(statement.isWrapperFor(FakeStatement.class));

        statement.close();
        connection.close();
        xaConnection.close();

        assertEquals(1, rawStatement.closeCount);
        assertEquals(1, rawConnection.closeCount);
        assertEquals(1, xaDelegate.openedConnections.get(0).closeCount);
        assertEquals(1, xaDelegate.openedConnections.get(0).connectionEventListeners.size());
        assertEquals(1, xaDelegate.openedConnections.get(0).statementEventListeners.size());
    }

    @Test
    void providerExceptionsPropagateWithoutWrapping() throws Exception {
        FakeDataSource delegate = new FakeDataSource();
        DataSource dataSource = JdbcProxy.wrap(delegate, "orders");
        Connection connection = dataSource.getConnection();
        FakeConnection rawConnection = (FakeConnection) InterfaceProxy.unwrap(connection);
        rawConnection.failingSql = "boom";
        Statement statement = connection.createStatement();

        SQLException failure = assertThrows(SQLException.class, () -> statement.executeQuery("boom"));
        assertEquals("provider boom", failure.getMessage());
    }

    @Test
    void depthOptionsCanDisableNestedWrapping() throws Exception {
        FakeDataSource delegate = new FakeDataSource();
        JdbcProxyOptions options = JdbcProxyOptions.builder().resultSets(false).build();
        DataSource dataSource = JdbcProxy.wrap(delegate, "orders", options);
        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select 1");

        assertFalse(resultSet instanceof io.github.rrobetti.japiproxy.core.ProxyHandle);
    }

    @Test
    void xaIsSameRmUnwrapsFrameworkProxyArguments() throws Exception {
        FakeXADataSource delegate = new FakeXADataSource();
        XADataSource xaDataSource = JdbcProxy.wrapXa(delegate, "xa");
        XAConnection firstConnection = xaDataSource.getXAConnection();
        XAConnection secondConnection = xaDataSource.getXAConnection();
        XAResource first = firstConnection.getXAResource();
        XAResource sameUnderlying = firstConnection.getXAResource();
        XAResource second = secondConnection.getXAResource();

        assertTrue(first.isSameRM(sameUnderlying));
        assertFalse(first.isSameRM(second));
    }

    @Test
    void sharedMonitoringFilterObservesJdbcCallsAcrossTheGraph() throws Exception {
        io.github.rrobetti.japiproxy.MonitoringFilter monitoring =
                new io.github.rrobetti.japiproxy.MonitoringFilter();
        FakeDataSource delegate = new FakeDataSource();
        DataSource dataSource = JdbcProxy.wrap(delegate, "orders", monitoring);

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();
        statement.executeQuery("select 1");

        assertEquals(1, monitoring.successCount(DataSource.class, "getConnection"));
        assertEquals(1, monitoring.successCount(Connection.class, "createStatement"));
        assertEquals(1, monitoring.successCount(Statement.class, "executeQuery"));
        assertEquals(0, monitoring.failureCount(Statement.class, "executeQuery"));
    }
}
