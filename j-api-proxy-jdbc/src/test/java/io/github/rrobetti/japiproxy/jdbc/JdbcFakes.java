package io.github.rrobetti.japiproxy.jdbc;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executor;
import javax.sql.ConnectionEventListener;
import javax.sql.StatementEventListener;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;

abstract class UnsupportedDataSource implements javax.sql.DataSource {
    @Override
    public java.sql.Connection getConnection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DataSource.getConnection");
    }

    @Override
    public java.sql.Connection getConnection(java.lang.String arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DataSource.getConnection");
    }

    @Override
    public java.io.PrintWriter getLogWriter() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DataSource.getLogWriter");
    }

    @Override
    public int getLoginTimeout() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DataSource.getLoginTimeout");
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws java.sql.SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not implemented: DataSource.getParentLogger");
    }

    @Override
    public boolean isWrapperFor(java.lang.Class<?> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DataSource.isWrapperFor");
    }

    @Override
    public void setLogWriter(java.io.PrintWriter arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DataSource.setLogWriter");
    }

    @Override
    public void setLoginTimeout(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DataSource.setLoginTimeout");
    }

    @Override
    public <T> T unwrap(java.lang.Class<T> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DataSource.unwrap");
    }

}

abstract class UnsupportedXADataSource implements javax.sql.XADataSource {
    @Override
    public java.io.PrintWriter getLogWriter() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: XADataSource.getLogWriter");
    }

    @Override
    public int getLoginTimeout() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: XADataSource.getLoginTimeout");
    }

    @Override
    public java.util.logging.Logger getParentLogger() throws java.sql.SQLFeatureNotSupportedException {
        throw new UnsupportedOperationException("Not implemented: XADataSource.getParentLogger");
    }

    @Override
    public javax.sql.XAConnection getXAConnection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: XADataSource.getXAConnection");
    }

    @Override
    public javax.sql.XAConnection getXAConnection(java.lang.String arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: XADataSource.getXAConnection");
    }

    @Override
    public void setLogWriter(java.io.PrintWriter arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: XADataSource.setLogWriter");
    }

    @Override
    public void setLoginTimeout(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: XADataSource.setLoginTimeout");
    }

}

abstract class UnsupportedConnection implements java.sql.Connection {
    @Override
    public void abort(java.util.concurrent.Executor arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.abort");
    }

    @Override
    public void clearWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.clearWarnings");
    }

    @Override
    public void close() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.close");
    }

    @Override
    public void commit() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.commit");
    }

    @Override
    public java.sql.Array createArrayOf(java.lang.String arg0, java.lang.Object[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.createArrayOf");
    }

    @Override
    public java.sql.Blob createBlob() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.createBlob");
    }

    @Override
    public java.sql.Clob createClob() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.createClob");
    }

    @Override
    public java.sql.NClob createNClob() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.createNClob");
    }

    @Override
    public java.sql.SQLXML createSQLXML() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.createSQLXML");
    }

    @Override
    public java.sql.Statement createStatement() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.createStatement");
    }

    @Override
    public java.sql.Statement createStatement(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.createStatement");
    }

    @Override
    public java.sql.Statement createStatement(int arg0, int arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.createStatement");
    }

    @Override
    public java.sql.Struct createStruct(java.lang.String arg0, java.lang.Object[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.createStruct");
    }

    @Override
    public boolean getAutoCommit() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getAutoCommit");
    }

    @Override
    public java.lang.String getCatalog() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getCatalog");
    }

    @Override
    public java.util.Properties getClientInfo() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getClientInfo");
    }

    @Override
    public java.lang.String getClientInfo(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getClientInfo");
    }

    @Override
    public int getHoldability() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getHoldability");
    }

    @Override
    public java.sql.DatabaseMetaData getMetaData() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getMetaData");
    }

    @Override
    public int getNetworkTimeout() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getNetworkTimeout");
    }

    @Override
    public java.lang.String getSchema() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getSchema");
    }

    @Override
    public int getTransactionIsolation() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getTransactionIsolation");
    }

    @Override
    public java.util.Map<java.lang.String, java.lang.Class<?>> getTypeMap() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getTypeMap");
    }

    @Override
    public java.sql.SQLWarning getWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.getWarnings");
    }

    @Override
    public boolean isClosed() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.isClosed");
    }

    @Override
    public boolean isReadOnly() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.isReadOnly");
    }

    @Override
    public boolean isValid(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.isValid");
    }

    @Override
    public boolean isWrapperFor(java.lang.Class<?> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.isWrapperFor");
    }

    @Override
    public java.lang.String nativeSQL(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.nativeSQL");
    }

    @Override
    public java.sql.CallableStatement prepareCall(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.prepareCall");
    }

    @Override
    public java.sql.CallableStatement prepareCall(java.lang.String arg0, int arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.prepareCall");
    }

    @Override
    public java.sql.CallableStatement prepareCall(java.lang.String arg0, int arg1, int arg2, int arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.prepareCall");
    }

    @Override
    public java.sql.PreparedStatement prepareStatement(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.prepareStatement");
    }

    @Override
    public java.sql.PreparedStatement prepareStatement(java.lang.String arg0, int[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.prepareStatement");
    }

    @Override
    public java.sql.PreparedStatement prepareStatement(java.lang.String arg0, java.lang.String[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.prepareStatement");
    }

    @Override
    public java.sql.PreparedStatement prepareStatement(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.prepareStatement");
    }

    @Override
    public java.sql.PreparedStatement prepareStatement(java.lang.String arg0, int arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.prepareStatement");
    }

    @Override
    public java.sql.PreparedStatement prepareStatement(java.lang.String arg0, int arg1, int arg2, int arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.prepareStatement");
    }

    @Override
    public void releaseSavepoint(java.sql.Savepoint arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.releaseSavepoint");
    }

    @Override
    public void rollback() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.rollback");
    }

    @Override
    public void rollback(java.sql.Savepoint arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.rollback");
    }

    @Override
    public void setAutoCommit(boolean arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setAutoCommit");
    }

    @Override
    public void setCatalog(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setCatalog");
    }

    @Override
    public void setClientInfo(java.util.Properties arg0) throws java.sql.SQLClientInfoException {
        throw new UnsupportedOperationException("Not implemented: Connection.setClientInfo");
    }

    @Override
    public void setClientInfo(java.lang.String arg0, java.lang.String arg1) throws java.sql.SQLClientInfoException {
        throw new UnsupportedOperationException("Not implemented: Connection.setClientInfo");
    }

    @Override
    public void setHoldability(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setHoldability");
    }

    @Override
    public void setNetworkTimeout(java.util.concurrent.Executor arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setNetworkTimeout");
    }

    @Override
    public void setReadOnly(boolean arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setReadOnly");
    }

    @Override
    public java.sql.Savepoint setSavepoint() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setSavepoint");
    }

    @Override
    public java.sql.Savepoint setSavepoint(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setSavepoint");
    }

    @Override
    public void setSchema(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setSchema");
    }

    @Override
    public void setTransactionIsolation(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setTransactionIsolation");
    }

    @Override
    public void setTypeMap(java.util.Map<java.lang.String, java.lang.Class<?>> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.setTypeMap");
    }

    @Override
    public <T> T unwrap(java.lang.Class<T> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Connection.unwrap");
    }

}

abstract class UnsupportedStatement implements java.sql.Statement {
    @Override
    public void addBatch(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.addBatch");
    }

    @Override
    public void cancel() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.cancel");
    }

    @Override
    public void clearBatch() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.clearBatch");
    }

    @Override
    public void clearWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.clearWarnings");
    }

    @Override
    public void close() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.close");
    }

    @Override
    public void closeOnCompletion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.closeOnCompletion");
    }

    @Override
    public boolean execute(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0, int[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0, java.lang.String[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.execute");
    }

    @Override
    public int[] executeBatch() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.executeBatch");
    }

    @Override
    public java.sql.ResultSet executeQuery(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.executeQuery");
    }

    @Override
    public int executeUpdate(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0, int[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0, java.lang.String[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.executeUpdate");
    }

    @Override
    public java.sql.Connection getConnection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getConnection");
    }

    @Override
    public int getFetchDirection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getFetchDirection");
    }

    @Override
    public int getFetchSize() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getFetchSize");
    }

    @Override
    public java.sql.ResultSet getGeneratedKeys() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getGeneratedKeys");
    }

    @Override
    public int getMaxFieldSize() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getMaxFieldSize");
    }

    @Override
    public int getMaxRows() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getMaxRows");
    }

    @Override
    public boolean getMoreResults() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getMoreResults");
    }

    @Override
    public boolean getMoreResults(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getMoreResults");
    }

    @Override
    public int getQueryTimeout() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getQueryTimeout");
    }

    @Override
    public java.sql.ResultSet getResultSet() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getResultSet");
    }

    @Override
    public int getResultSetConcurrency() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getResultSetConcurrency");
    }

    @Override
    public int getResultSetHoldability() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getResultSetHoldability");
    }

    @Override
    public int getResultSetType() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getResultSetType");
    }

    @Override
    public int getUpdateCount() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getUpdateCount");
    }

    @Override
    public java.sql.SQLWarning getWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.getWarnings");
    }

    @Override
    public boolean isCloseOnCompletion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.isCloseOnCompletion");
    }

    @Override
    public boolean isClosed() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.isClosed");
    }

    @Override
    public boolean isPoolable() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.isPoolable");
    }

    @Override
    public boolean isWrapperFor(java.lang.Class<?> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.isWrapperFor");
    }

    @Override
    public void setCursorName(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.setCursorName");
    }

    @Override
    public void setEscapeProcessing(boolean arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.setEscapeProcessing");
    }

    @Override
    public void setFetchDirection(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.setFetchDirection");
    }

    @Override
    public void setFetchSize(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.setFetchSize");
    }

    @Override
    public void setMaxFieldSize(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.setMaxFieldSize");
    }

    @Override
    public void setMaxRows(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.setMaxRows");
    }

    @Override
    public void setPoolable(boolean arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.setPoolable");
    }

    @Override
    public void setQueryTimeout(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.setQueryTimeout");
    }

    @Override
    public <T> T unwrap(java.lang.Class<T> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: Statement.unwrap");
    }

}

abstract class UnsupportedPreparedStatement implements java.sql.PreparedStatement {
    @Override
    public void addBatch() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.addBatch");
    }

    @Override
    public void addBatch(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.addBatch");
    }

    @Override
    public void cancel() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.cancel");
    }

    @Override
    public void clearBatch() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.clearBatch");
    }

    @Override
    public void clearParameters() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.clearParameters");
    }

    @Override
    public void clearWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.clearWarnings");
    }

    @Override
    public void close() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.close");
    }

    @Override
    public void closeOnCompletion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.closeOnCompletion");
    }

    @Override
    public boolean execute() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0, int[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0, java.lang.String[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.execute");
    }

    @Override
    public int[] executeBatch() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.executeBatch");
    }

    @Override
    public java.sql.ResultSet executeQuery() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.executeQuery");
    }

    @Override
    public java.sql.ResultSet executeQuery(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.executeQuery");
    }

    @Override
    public int executeUpdate() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0, int[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0, java.lang.String[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.executeUpdate");
    }

    @Override
    public java.sql.Connection getConnection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getConnection");
    }

    @Override
    public int getFetchDirection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getFetchDirection");
    }

    @Override
    public int getFetchSize() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getFetchSize");
    }

    @Override
    public java.sql.ResultSet getGeneratedKeys() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getGeneratedKeys");
    }

    @Override
    public int getMaxFieldSize() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getMaxFieldSize");
    }

    @Override
    public int getMaxRows() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getMaxRows");
    }

    @Override
    public java.sql.ResultSetMetaData getMetaData() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getMetaData");
    }

    @Override
    public boolean getMoreResults() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getMoreResults");
    }

    @Override
    public boolean getMoreResults(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getMoreResults");
    }

    @Override
    public java.sql.ParameterMetaData getParameterMetaData() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getParameterMetaData");
    }

    @Override
    public int getQueryTimeout() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getQueryTimeout");
    }

    @Override
    public java.sql.ResultSet getResultSet() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getResultSet");
    }

    @Override
    public int getResultSetConcurrency() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getResultSetConcurrency");
    }

    @Override
    public int getResultSetHoldability() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getResultSetHoldability");
    }

    @Override
    public int getResultSetType() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getResultSetType");
    }

    @Override
    public int getUpdateCount() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getUpdateCount");
    }

    @Override
    public java.sql.SQLWarning getWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.getWarnings");
    }

    @Override
    public boolean isCloseOnCompletion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.isCloseOnCompletion");
    }

    @Override
    public boolean isClosed() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.isClosed");
    }

    @Override
    public boolean isPoolable() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.isPoolable");
    }

    @Override
    public boolean isWrapperFor(java.lang.Class<?> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.isWrapperFor");
    }

    @Override
    public void setArray(int arg0, java.sql.Array arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setArray");
    }

    @Override
    public void setAsciiStream(int arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setAsciiStream");
    }

    @Override
    public void setAsciiStream(int arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setAsciiStream");
    }

    @Override
    public void setAsciiStream(int arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setAsciiStream");
    }

    @Override
    public void setBigDecimal(int arg0, java.math.BigDecimal arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setBigDecimal");
    }

    @Override
    public void setBinaryStream(int arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setBinaryStream");
    }

    @Override
    public void setBinaryStream(int arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setBinaryStream");
    }

    @Override
    public void setBinaryStream(int arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setBinaryStream");
    }

    @Override
    public void setBlob(int arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setBlob");
    }

    @Override
    public void setBlob(int arg0, java.sql.Blob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setBlob");
    }

    @Override
    public void setBlob(int arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setBlob");
    }

    @Override
    public void setBoolean(int arg0, boolean arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setBoolean");
    }

    @Override
    public void setByte(int arg0, byte arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setByte");
    }

    @Override
    public void setBytes(int arg0, byte[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setBytes");
    }

    @Override
    public void setCharacterStream(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setCharacterStream");
    }

    @Override
    public void setCharacterStream(int arg0, java.io.Reader arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setCharacterStream");
    }

    @Override
    public void setCharacterStream(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setCharacterStream");
    }

    @Override
    public void setClob(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setClob");
    }

    @Override
    public void setClob(int arg0, java.sql.Clob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setClob");
    }

    @Override
    public void setClob(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setClob");
    }

    @Override
    public void setCursorName(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setCursorName");
    }

    @Override
    public void setDate(int arg0, java.sql.Date arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setDate");
    }

    @Override
    public void setDate(int arg0, java.sql.Date arg1, java.util.Calendar arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setDate");
    }

    @Override
    public void setDouble(int arg0, double arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setDouble");
    }

    @Override
    public void setEscapeProcessing(boolean arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setEscapeProcessing");
    }

    @Override
    public void setFetchDirection(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setFetchDirection");
    }

    @Override
    public void setFetchSize(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setFetchSize");
    }

    @Override
    public void setFloat(int arg0, float arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setFloat");
    }

    @Override
    public void setInt(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setInt");
    }

    @Override
    public void setLong(int arg0, long arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setLong");
    }

    @Override
    public void setMaxFieldSize(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setMaxFieldSize");
    }

    @Override
    public void setMaxRows(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setMaxRows");
    }

    @Override
    public void setNCharacterStream(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setNCharacterStream");
    }

    @Override
    public void setNCharacterStream(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setNCharacterStream");
    }

    @Override
    public void setNClob(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setNClob");
    }

    @Override
    public void setNClob(int arg0, java.sql.NClob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setNClob");
    }

    @Override
    public void setNClob(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setNClob");
    }

    @Override
    public void setNString(int arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setNString");
    }

    @Override
    public void setNull(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setNull");
    }

    @Override
    public void setNull(int arg0, int arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setNull");
    }

    @Override
    public void setObject(int arg0, java.lang.Object arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setObject");
    }

    @Override
    public void setObject(int arg0, java.lang.Object arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setObject");
    }

    @Override
    public void setObject(int arg0, java.lang.Object arg1, int arg2, int arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setObject");
    }

    @Override
    public void setPoolable(boolean arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setPoolable");
    }

    @Override
    public void setQueryTimeout(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setQueryTimeout");
    }

    @Override
    public void setRef(int arg0, java.sql.Ref arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setRef");
    }

    @Override
    public void setRowId(int arg0, java.sql.RowId arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setRowId");
    }

    @Override
    public void setSQLXML(int arg0, java.sql.SQLXML arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setSQLXML");
    }

    @Override
    public void setShort(int arg0, short arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setShort");
    }

    @Override
    public void setString(int arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setString");
    }

    @Override
    public void setTime(int arg0, java.sql.Time arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setTime");
    }

    @Override
    public void setTime(int arg0, java.sql.Time arg1, java.util.Calendar arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setTime");
    }

    @Override
    public void setTimestamp(int arg0, java.sql.Timestamp arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setTimestamp");
    }

    @Override
    public void setTimestamp(int arg0, java.sql.Timestamp arg1, java.util.Calendar arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setTimestamp");
    }

    @Override
    public void setURL(int arg0, java.net.URL arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setURL");
    }

    @Override
    public void setUnicodeStream(int arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.setUnicodeStream");
    }

    @Override
    public <T> T unwrap(java.lang.Class<T> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: PreparedStatement.unwrap");
    }

}

abstract class UnsupportedCallableStatement implements java.sql.CallableStatement {
    @Override
    public void addBatch() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.addBatch");
    }

    @Override
    public void addBatch(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.addBatch");
    }

    @Override
    public void cancel() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.cancel");
    }

    @Override
    public void clearBatch() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.clearBatch");
    }

    @Override
    public void clearParameters() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.clearParameters");
    }

    @Override
    public void clearWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.clearWarnings");
    }

    @Override
    public void close() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.close");
    }

    @Override
    public void closeOnCompletion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.closeOnCompletion");
    }

    @Override
    public boolean execute() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0, int[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0, java.lang.String[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.execute");
    }

    @Override
    public boolean execute(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.execute");
    }

    @Override
    public int[] executeBatch() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.executeBatch");
    }

    @Override
    public java.sql.ResultSet executeQuery() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.executeQuery");
    }

    @Override
    public java.sql.ResultSet executeQuery(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.executeQuery");
    }

    @Override
    public int executeUpdate() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0, int[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0, java.lang.String[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.executeUpdate");
    }

    @Override
    public int executeUpdate(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.executeUpdate");
    }

    @Override
    public java.sql.Array getArray(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getArray");
    }

    @Override
    public java.sql.Array getArray(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getArray");
    }

    @Override
    public java.math.BigDecimal getBigDecimal(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getBigDecimal");
    }

    @Override
    public java.math.BigDecimal getBigDecimal(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getBigDecimal");
    }

    @Override
    public java.math.BigDecimal getBigDecimal(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getBigDecimal");
    }

    @Override
    public java.sql.Blob getBlob(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getBlob");
    }

    @Override
    public java.sql.Blob getBlob(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getBlob");
    }

    @Override
    public boolean getBoolean(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getBoolean");
    }

    @Override
    public boolean getBoolean(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getBoolean");
    }

    @Override
    public byte getByte(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getByte");
    }

    @Override
    public byte getByte(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getByte");
    }

    @Override
    public byte[] getBytes(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getBytes");
    }

    @Override
    public byte[] getBytes(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getBytes");
    }

    @Override
    public java.io.Reader getCharacterStream(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getCharacterStream");
    }

    @Override
    public java.io.Reader getCharacterStream(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getCharacterStream");
    }

    @Override
    public java.sql.Clob getClob(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getClob");
    }

    @Override
    public java.sql.Clob getClob(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getClob");
    }

    @Override
    public java.sql.Connection getConnection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getConnection");
    }

    @Override
    public java.sql.Date getDate(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getDate");
    }

    @Override
    public java.sql.Date getDate(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getDate");
    }

    @Override
    public java.sql.Date getDate(java.lang.String arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getDate");
    }

    @Override
    public java.sql.Date getDate(int arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getDate");
    }

    @Override
    public double getDouble(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getDouble");
    }

    @Override
    public double getDouble(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getDouble");
    }

    @Override
    public int getFetchDirection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getFetchDirection");
    }

    @Override
    public int getFetchSize() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getFetchSize");
    }

    @Override
    public float getFloat(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getFloat");
    }

    @Override
    public float getFloat(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getFloat");
    }

    @Override
    public java.sql.ResultSet getGeneratedKeys() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getGeneratedKeys");
    }

    @Override
    public int getInt(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getInt");
    }

    @Override
    public int getInt(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getInt");
    }

    @Override
    public long getLong(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getLong");
    }

    @Override
    public long getLong(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getLong");
    }

    @Override
    public int getMaxFieldSize() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getMaxFieldSize");
    }

    @Override
    public int getMaxRows() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getMaxRows");
    }

    @Override
    public java.sql.ResultSetMetaData getMetaData() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getMetaData");
    }

    @Override
    public boolean getMoreResults() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getMoreResults");
    }

    @Override
    public boolean getMoreResults(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getMoreResults");
    }

    @Override
    public java.io.Reader getNCharacterStream(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getNCharacterStream");
    }

    @Override
    public java.io.Reader getNCharacterStream(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getNCharacterStream");
    }

    @Override
    public java.sql.NClob getNClob(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getNClob");
    }

    @Override
    public java.sql.NClob getNClob(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getNClob");
    }

    @Override
    public java.lang.String getNString(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getNString");
    }

    @Override
    public java.lang.String getNString(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getNString");
    }

    @Override
    public java.lang.Object getObject(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getObject");
    }

    @Override
    public java.lang.Object getObject(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getObject");
    }

    @Override
    public <T> T getObject(java.lang.String arg0, java.lang.Class<T> arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getObject");
    }

    @Override
    public java.lang.Object getObject(java.lang.String arg0, java.util.Map<java.lang.String, java.lang.Class<?>> arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getObject");
    }

    @Override
    public <T> T getObject(int arg0, java.lang.Class<T> arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getObject");
    }

    @Override
    public java.lang.Object getObject(int arg0, java.util.Map<java.lang.String, java.lang.Class<?>> arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getObject");
    }

    @Override
    public java.sql.ParameterMetaData getParameterMetaData() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getParameterMetaData");
    }

    @Override
    public int getQueryTimeout() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getQueryTimeout");
    }

    @Override
    public java.sql.Ref getRef(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getRef");
    }

    @Override
    public java.sql.Ref getRef(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getRef");
    }

    @Override
    public java.sql.ResultSet getResultSet() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getResultSet");
    }

    @Override
    public int getResultSetConcurrency() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getResultSetConcurrency");
    }

    @Override
    public int getResultSetHoldability() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getResultSetHoldability");
    }

    @Override
    public int getResultSetType() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getResultSetType");
    }

    @Override
    public java.sql.RowId getRowId(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getRowId");
    }

    @Override
    public java.sql.RowId getRowId(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getRowId");
    }

    @Override
    public java.sql.SQLXML getSQLXML(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getSQLXML");
    }

    @Override
    public java.sql.SQLXML getSQLXML(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getSQLXML");
    }

    @Override
    public short getShort(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getShort");
    }

    @Override
    public short getShort(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getShort");
    }

    @Override
    public java.lang.String getString(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getString");
    }

    @Override
    public java.lang.String getString(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getString");
    }

    @Override
    public java.sql.Time getTime(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getTime");
    }

    @Override
    public java.sql.Time getTime(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getTime");
    }

    @Override
    public java.sql.Time getTime(java.lang.String arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getTime");
    }

    @Override
    public java.sql.Time getTime(int arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getTime");
    }

    @Override
    public java.sql.Timestamp getTimestamp(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getTimestamp");
    }

    @Override
    public java.sql.Timestamp getTimestamp(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getTimestamp");
    }

    @Override
    public java.sql.Timestamp getTimestamp(java.lang.String arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getTimestamp");
    }

    @Override
    public java.sql.Timestamp getTimestamp(int arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getTimestamp");
    }

    @Override
    public java.net.URL getURL(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getURL");
    }

    @Override
    public java.net.URL getURL(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getURL");
    }

    @Override
    public int getUpdateCount() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getUpdateCount");
    }

    @Override
    public java.sql.SQLWarning getWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.getWarnings");
    }

    @Override
    public boolean isCloseOnCompletion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.isCloseOnCompletion");
    }

    @Override
    public boolean isClosed() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.isClosed");
    }

    @Override
    public boolean isPoolable() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.isPoolable");
    }

    @Override
    public boolean isWrapperFor(java.lang.Class<?> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.isWrapperFor");
    }

    @Override
    public void registerOutParameter(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.registerOutParameter");
    }

    @Override
    public void registerOutParameter(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.registerOutParameter");
    }

    @Override
    public void registerOutParameter(java.lang.String arg0, int arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.registerOutParameter");
    }

    @Override
    public void registerOutParameter(java.lang.String arg0, int arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.registerOutParameter");
    }

    @Override
    public void registerOutParameter(int arg0, int arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.registerOutParameter");
    }

    @Override
    public void registerOutParameter(int arg0, int arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.registerOutParameter");
    }

    @Override
    public void setArray(int arg0, java.sql.Array arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setArray");
    }

    @Override
    public void setAsciiStream(java.lang.String arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setAsciiStream");
    }

    @Override
    public void setAsciiStream(int arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setAsciiStream");
    }

    @Override
    public void setAsciiStream(java.lang.String arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setAsciiStream");
    }

    @Override
    public void setAsciiStream(java.lang.String arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setAsciiStream");
    }

    @Override
    public void setAsciiStream(int arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setAsciiStream");
    }

    @Override
    public void setAsciiStream(int arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setAsciiStream");
    }

    @Override
    public void setBigDecimal(java.lang.String arg0, java.math.BigDecimal arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBigDecimal");
    }

    @Override
    public void setBigDecimal(int arg0, java.math.BigDecimal arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBigDecimal");
    }

    @Override
    public void setBinaryStream(java.lang.String arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBinaryStream");
    }

    @Override
    public void setBinaryStream(int arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBinaryStream");
    }

    @Override
    public void setBinaryStream(java.lang.String arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBinaryStream");
    }

    @Override
    public void setBinaryStream(java.lang.String arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBinaryStream");
    }

    @Override
    public void setBinaryStream(int arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBinaryStream");
    }

    @Override
    public void setBinaryStream(int arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBinaryStream");
    }

    @Override
    public void setBlob(java.lang.String arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBlob");
    }

    @Override
    public void setBlob(java.lang.String arg0, java.sql.Blob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBlob");
    }

    @Override
    public void setBlob(int arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBlob");
    }

    @Override
    public void setBlob(int arg0, java.sql.Blob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBlob");
    }

    @Override
    public void setBlob(java.lang.String arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBlob");
    }

    @Override
    public void setBlob(int arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBlob");
    }

    @Override
    public void setBoolean(java.lang.String arg0, boolean arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBoolean");
    }

    @Override
    public void setBoolean(int arg0, boolean arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBoolean");
    }

    @Override
    public void setByte(java.lang.String arg0, byte arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setByte");
    }

    @Override
    public void setByte(int arg0, byte arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setByte");
    }

    @Override
    public void setBytes(java.lang.String arg0, byte[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBytes");
    }

    @Override
    public void setBytes(int arg0, byte[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setBytes");
    }

    @Override
    public void setCharacterStream(java.lang.String arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setCharacterStream");
    }

    @Override
    public void setCharacterStream(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setCharacterStream");
    }

    @Override
    public void setCharacterStream(java.lang.String arg0, java.io.Reader arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setCharacterStream");
    }

    @Override
    public void setCharacterStream(java.lang.String arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setCharacterStream");
    }

    @Override
    public void setCharacterStream(int arg0, java.io.Reader arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setCharacterStream");
    }

    @Override
    public void setCharacterStream(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setCharacterStream");
    }

    @Override
    public void setClob(java.lang.String arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setClob");
    }

    @Override
    public void setClob(java.lang.String arg0, java.sql.Clob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setClob");
    }

    @Override
    public void setClob(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setClob");
    }

    @Override
    public void setClob(int arg0, java.sql.Clob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setClob");
    }

    @Override
    public void setClob(java.lang.String arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setClob");
    }

    @Override
    public void setClob(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setClob");
    }

    @Override
    public void setCursorName(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setCursorName");
    }

    @Override
    public void setDate(java.lang.String arg0, java.sql.Date arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setDate");
    }

    @Override
    public void setDate(int arg0, java.sql.Date arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setDate");
    }

    @Override
    public void setDate(java.lang.String arg0, java.sql.Date arg1, java.util.Calendar arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setDate");
    }

    @Override
    public void setDate(int arg0, java.sql.Date arg1, java.util.Calendar arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setDate");
    }

    @Override
    public void setDouble(java.lang.String arg0, double arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setDouble");
    }

    @Override
    public void setDouble(int arg0, double arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setDouble");
    }

    @Override
    public void setEscapeProcessing(boolean arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setEscapeProcessing");
    }

    @Override
    public void setFetchDirection(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setFetchDirection");
    }

    @Override
    public void setFetchSize(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setFetchSize");
    }

    @Override
    public void setFloat(java.lang.String arg0, float arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setFloat");
    }

    @Override
    public void setFloat(int arg0, float arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setFloat");
    }

    @Override
    public void setInt(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setInt");
    }

    @Override
    public void setInt(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setInt");
    }

    @Override
    public void setLong(java.lang.String arg0, long arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setLong");
    }

    @Override
    public void setLong(int arg0, long arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setLong");
    }

    @Override
    public void setMaxFieldSize(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setMaxFieldSize");
    }

    @Override
    public void setMaxRows(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setMaxRows");
    }

    @Override
    public void setNCharacterStream(java.lang.String arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNCharacterStream");
    }

    @Override
    public void setNCharacterStream(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNCharacterStream");
    }

    @Override
    public void setNCharacterStream(java.lang.String arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNCharacterStream");
    }

    @Override
    public void setNCharacterStream(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNCharacterStream");
    }

    @Override
    public void setNClob(java.lang.String arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNClob");
    }

    @Override
    public void setNClob(java.lang.String arg0, java.sql.NClob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNClob");
    }

    @Override
    public void setNClob(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNClob");
    }

    @Override
    public void setNClob(int arg0, java.sql.NClob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNClob");
    }

    @Override
    public void setNClob(java.lang.String arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNClob");
    }

    @Override
    public void setNClob(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNClob");
    }

    @Override
    public void setNString(java.lang.String arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNString");
    }

    @Override
    public void setNString(int arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNString");
    }

    @Override
    public void setNull(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNull");
    }

    @Override
    public void setNull(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNull");
    }

    @Override
    public void setNull(java.lang.String arg0, int arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNull");
    }

    @Override
    public void setNull(int arg0, int arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setNull");
    }

    @Override
    public void setObject(java.lang.String arg0, java.lang.Object arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setObject");
    }

    @Override
    public void setObject(int arg0, java.lang.Object arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setObject");
    }

    @Override
    public void setObject(java.lang.String arg0, java.lang.Object arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setObject");
    }

    @Override
    public void setObject(int arg0, java.lang.Object arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setObject");
    }

    @Override
    public void setObject(java.lang.String arg0, java.lang.Object arg1, int arg2, int arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setObject");
    }

    @Override
    public void setObject(int arg0, java.lang.Object arg1, int arg2, int arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setObject");
    }

    @Override
    public void setPoolable(boolean arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setPoolable");
    }

    @Override
    public void setQueryTimeout(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setQueryTimeout");
    }

    @Override
    public void setRef(int arg0, java.sql.Ref arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setRef");
    }

    @Override
    public void setRowId(java.lang.String arg0, java.sql.RowId arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setRowId");
    }

    @Override
    public void setRowId(int arg0, java.sql.RowId arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setRowId");
    }

    @Override
    public void setSQLXML(java.lang.String arg0, java.sql.SQLXML arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setSQLXML");
    }

    @Override
    public void setSQLXML(int arg0, java.sql.SQLXML arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setSQLXML");
    }

    @Override
    public void setShort(java.lang.String arg0, short arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setShort");
    }

    @Override
    public void setShort(int arg0, short arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setShort");
    }

    @Override
    public void setString(java.lang.String arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setString");
    }

    @Override
    public void setString(int arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setString");
    }

    @Override
    public void setTime(java.lang.String arg0, java.sql.Time arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setTime");
    }

    @Override
    public void setTime(int arg0, java.sql.Time arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setTime");
    }

    @Override
    public void setTime(java.lang.String arg0, java.sql.Time arg1, java.util.Calendar arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setTime");
    }

    @Override
    public void setTime(int arg0, java.sql.Time arg1, java.util.Calendar arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setTime");
    }

    @Override
    public void setTimestamp(java.lang.String arg0, java.sql.Timestamp arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setTimestamp");
    }

    @Override
    public void setTimestamp(int arg0, java.sql.Timestamp arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setTimestamp");
    }

    @Override
    public void setTimestamp(java.lang.String arg0, java.sql.Timestamp arg1, java.util.Calendar arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setTimestamp");
    }

    @Override
    public void setTimestamp(int arg0, java.sql.Timestamp arg1, java.util.Calendar arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setTimestamp");
    }

    @Override
    public void setURL(java.lang.String arg0, java.net.URL arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setURL");
    }

    @Override
    public void setURL(int arg0, java.net.URL arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setURL");
    }

    @Override
    public void setUnicodeStream(int arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.setUnicodeStream");
    }

    @Override
    public <T> T unwrap(java.lang.Class<T> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.unwrap");
    }

    @Override
    public boolean wasNull() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: CallableStatement.wasNull");
    }

}

abstract class UnsupportedResultSet implements java.sql.ResultSet {
    @Override
    public boolean absolute(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.absolute");
    }

    @Override
    public void afterLast() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.afterLast");
    }

    @Override
    public void beforeFirst() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.beforeFirst");
    }

    @Override
    public void cancelRowUpdates() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.cancelRowUpdates");
    }

    @Override
    public void clearWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.clearWarnings");
    }

    @Override
    public void close() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.close");
    }

    @Override
    public void deleteRow() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.deleteRow");
    }

    @Override
    public int findColumn(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.findColumn");
    }

    @Override
    public boolean first() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.first");
    }

    @Override
    public java.sql.Array getArray(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getArray");
    }

    @Override
    public java.sql.Array getArray(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getArray");
    }

    @Override
    public java.io.InputStream getAsciiStream(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getAsciiStream");
    }

    @Override
    public java.io.InputStream getAsciiStream(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getAsciiStream");
    }

    @Override
    public java.math.BigDecimal getBigDecimal(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBigDecimal");
    }

    @Override
    public java.math.BigDecimal getBigDecimal(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBigDecimal");
    }

    @Override
    public java.math.BigDecimal getBigDecimal(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBigDecimal");
    }

    @Override
    public java.math.BigDecimal getBigDecimal(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBigDecimal");
    }

    @Override
    public java.io.InputStream getBinaryStream(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBinaryStream");
    }

    @Override
    public java.io.InputStream getBinaryStream(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBinaryStream");
    }

    @Override
    public java.sql.Blob getBlob(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBlob");
    }

    @Override
    public java.sql.Blob getBlob(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBlob");
    }

    @Override
    public boolean getBoolean(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBoolean");
    }

    @Override
    public boolean getBoolean(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBoolean");
    }

    @Override
    public byte getByte(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getByte");
    }

    @Override
    public byte getByte(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getByte");
    }

    @Override
    public byte[] getBytes(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBytes");
    }

    @Override
    public byte[] getBytes(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getBytes");
    }

    @Override
    public java.io.Reader getCharacterStream(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getCharacterStream");
    }

    @Override
    public java.io.Reader getCharacterStream(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getCharacterStream");
    }

    @Override
    public java.sql.Clob getClob(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getClob");
    }

    @Override
    public java.sql.Clob getClob(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getClob");
    }

    @Override
    public int getConcurrency() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getConcurrency");
    }

    @Override
    public java.lang.String getCursorName() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getCursorName");
    }

    @Override
    public java.sql.Date getDate(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getDate");
    }

    @Override
    public java.sql.Date getDate(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getDate");
    }

    @Override
    public java.sql.Date getDate(java.lang.String arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getDate");
    }

    @Override
    public java.sql.Date getDate(int arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getDate");
    }

    @Override
    public double getDouble(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getDouble");
    }

    @Override
    public double getDouble(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getDouble");
    }

    @Override
    public int getFetchDirection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getFetchDirection");
    }

    @Override
    public int getFetchSize() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getFetchSize");
    }

    @Override
    public float getFloat(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getFloat");
    }

    @Override
    public float getFloat(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getFloat");
    }

    @Override
    public int getHoldability() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getHoldability");
    }

    @Override
    public int getInt(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getInt");
    }

    @Override
    public int getInt(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getInt");
    }

    @Override
    public long getLong(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getLong");
    }

    @Override
    public long getLong(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getLong");
    }

    @Override
    public java.sql.ResultSetMetaData getMetaData() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getMetaData");
    }

    @Override
    public java.io.Reader getNCharacterStream(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getNCharacterStream");
    }

    @Override
    public java.io.Reader getNCharacterStream(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getNCharacterStream");
    }

    @Override
    public java.sql.NClob getNClob(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getNClob");
    }

    @Override
    public java.sql.NClob getNClob(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getNClob");
    }

    @Override
    public java.lang.String getNString(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getNString");
    }

    @Override
    public java.lang.String getNString(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getNString");
    }

    @Override
    public java.lang.Object getObject(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getObject");
    }

    @Override
    public java.lang.Object getObject(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getObject");
    }

    @Override
    public <T> T getObject(java.lang.String arg0, java.lang.Class<T> arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getObject");
    }

    @Override
    public java.lang.Object getObject(java.lang.String arg0, java.util.Map<java.lang.String, java.lang.Class<?>> arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getObject");
    }

    @Override
    public <T> T getObject(int arg0, java.lang.Class<T> arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getObject");
    }

    @Override
    public java.lang.Object getObject(int arg0, java.util.Map<java.lang.String, java.lang.Class<?>> arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getObject");
    }

    @Override
    public java.sql.Ref getRef(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getRef");
    }

    @Override
    public java.sql.Ref getRef(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getRef");
    }

    @Override
    public int getRow() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getRow");
    }

    @Override
    public java.sql.RowId getRowId(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getRowId");
    }

    @Override
    public java.sql.RowId getRowId(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getRowId");
    }

    @Override
    public java.sql.SQLXML getSQLXML(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getSQLXML");
    }

    @Override
    public java.sql.SQLXML getSQLXML(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getSQLXML");
    }

    @Override
    public short getShort(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getShort");
    }

    @Override
    public short getShort(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getShort");
    }

    @Override
    public java.sql.Statement getStatement() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getStatement");
    }

    @Override
    public java.lang.String getString(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getString");
    }

    @Override
    public java.lang.String getString(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getString");
    }

    @Override
    public java.sql.Time getTime(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getTime");
    }

    @Override
    public java.sql.Time getTime(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getTime");
    }

    @Override
    public java.sql.Time getTime(java.lang.String arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getTime");
    }

    @Override
    public java.sql.Time getTime(int arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getTime");
    }

    @Override
    public java.sql.Timestamp getTimestamp(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getTimestamp");
    }

    @Override
    public java.sql.Timestamp getTimestamp(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getTimestamp");
    }

    @Override
    public java.sql.Timestamp getTimestamp(java.lang.String arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getTimestamp");
    }

    @Override
    public java.sql.Timestamp getTimestamp(int arg0, java.util.Calendar arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getTimestamp");
    }

    @Override
    public int getType() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getType");
    }

    @Override
    public java.net.URL getURL(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getURL");
    }

    @Override
    public java.net.URL getURL(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getURL");
    }

    @Override
    public java.io.InputStream getUnicodeStream(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getUnicodeStream");
    }

    @Override
    public java.io.InputStream getUnicodeStream(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getUnicodeStream");
    }

    @Override
    public java.sql.SQLWarning getWarnings() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.getWarnings");
    }

    @Override
    public void insertRow() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.insertRow");
    }

    @Override
    public boolean isAfterLast() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.isAfterLast");
    }

    @Override
    public boolean isBeforeFirst() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.isBeforeFirst");
    }

    @Override
    public boolean isClosed() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.isClosed");
    }

    @Override
    public boolean isFirst() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.isFirst");
    }

    @Override
    public boolean isLast() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.isLast");
    }

    @Override
    public boolean isWrapperFor(java.lang.Class<?> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.isWrapperFor");
    }

    @Override
    public boolean last() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.last");
    }

    @Override
    public void moveToCurrentRow() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.moveToCurrentRow");
    }

    @Override
    public void moveToInsertRow() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.moveToInsertRow");
    }

    @Override
    public boolean next() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.next");
    }

    @Override
    public boolean previous() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.previous");
    }

    @Override
    public void refreshRow() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.refreshRow");
    }

    @Override
    public boolean relative(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.relative");
    }

    @Override
    public boolean rowDeleted() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.rowDeleted");
    }

    @Override
    public boolean rowInserted() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.rowInserted");
    }

    @Override
    public boolean rowUpdated() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.rowUpdated");
    }

    @Override
    public void setFetchDirection(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.setFetchDirection");
    }

    @Override
    public void setFetchSize(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.setFetchSize");
    }

    @Override
    public <T> T unwrap(java.lang.Class<T> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.unwrap");
    }

    @Override
    public void updateArray(java.lang.String arg0, java.sql.Array arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateArray");
    }

    @Override
    public void updateArray(int arg0, java.sql.Array arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateArray");
    }

    @Override
    public void updateAsciiStream(java.lang.String arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateAsciiStream");
    }

    @Override
    public void updateAsciiStream(int arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateAsciiStream");
    }

    @Override
    public void updateAsciiStream(java.lang.String arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateAsciiStream");
    }

    @Override
    public void updateAsciiStream(java.lang.String arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateAsciiStream");
    }

    @Override
    public void updateAsciiStream(int arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateAsciiStream");
    }

    @Override
    public void updateAsciiStream(int arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateAsciiStream");
    }

    @Override
    public void updateBigDecimal(java.lang.String arg0, java.math.BigDecimal arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBigDecimal");
    }

    @Override
    public void updateBigDecimal(int arg0, java.math.BigDecimal arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBigDecimal");
    }

    @Override
    public void updateBinaryStream(java.lang.String arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBinaryStream");
    }

    @Override
    public void updateBinaryStream(int arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBinaryStream");
    }

    @Override
    public void updateBinaryStream(java.lang.String arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBinaryStream");
    }

    @Override
    public void updateBinaryStream(java.lang.String arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBinaryStream");
    }

    @Override
    public void updateBinaryStream(int arg0, java.io.InputStream arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBinaryStream");
    }

    @Override
    public void updateBinaryStream(int arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBinaryStream");
    }

    @Override
    public void updateBlob(java.lang.String arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBlob");
    }

    @Override
    public void updateBlob(java.lang.String arg0, java.sql.Blob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBlob");
    }

    @Override
    public void updateBlob(int arg0, java.io.InputStream arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBlob");
    }

    @Override
    public void updateBlob(int arg0, java.sql.Blob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBlob");
    }

    @Override
    public void updateBlob(java.lang.String arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBlob");
    }

    @Override
    public void updateBlob(int arg0, java.io.InputStream arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBlob");
    }

    @Override
    public void updateBoolean(java.lang.String arg0, boolean arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBoolean");
    }

    @Override
    public void updateBoolean(int arg0, boolean arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBoolean");
    }

    @Override
    public void updateByte(java.lang.String arg0, byte arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateByte");
    }

    @Override
    public void updateByte(int arg0, byte arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateByte");
    }

    @Override
    public void updateBytes(java.lang.String arg0, byte[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBytes");
    }

    @Override
    public void updateBytes(int arg0, byte[] arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateBytes");
    }

    @Override
    public void updateCharacterStream(java.lang.String arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateCharacterStream");
    }

    @Override
    public void updateCharacterStream(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateCharacterStream");
    }

    @Override
    public void updateCharacterStream(java.lang.String arg0, java.io.Reader arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateCharacterStream");
    }

    @Override
    public void updateCharacterStream(java.lang.String arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateCharacterStream");
    }

    @Override
    public void updateCharacterStream(int arg0, java.io.Reader arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateCharacterStream");
    }

    @Override
    public void updateCharacterStream(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateCharacterStream");
    }

    @Override
    public void updateClob(java.lang.String arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateClob");
    }

    @Override
    public void updateClob(java.lang.String arg0, java.sql.Clob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateClob");
    }

    @Override
    public void updateClob(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateClob");
    }

    @Override
    public void updateClob(int arg0, java.sql.Clob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateClob");
    }

    @Override
    public void updateClob(java.lang.String arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateClob");
    }

    @Override
    public void updateClob(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateClob");
    }

    @Override
    public void updateDate(java.lang.String arg0, java.sql.Date arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateDate");
    }

    @Override
    public void updateDate(int arg0, java.sql.Date arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateDate");
    }

    @Override
    public void updateDouble(java.lang.String arg0, double arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateDouble");
    }

    @Override
    public void updateDouble(int arg0, double arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateDouble");
    }

    @Override
    public void updateFloat(java.lang.String arg0, float arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateFloat");
    }

    @Override
    public void updateFloat(int arg0, float arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateFloat");
    }

    @Override
    public void updateInt(java.lang.String arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateInt");
    }

    @Override
    public void updateInt(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateInt");
    }

    @Override
    public void updateLong(java.lang.String arg0, long arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateLong");
    }

    @Override
    public void updateLong(int arg0, long arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateLong");
    }

    @Override
    public void updateNCharacterStream(java.lang.String arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNCharacterStream");
    }

    @Override
    public void updateNCharacterStream(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNCharacterStream");
    }

    @Override
    public void updateNCharacterStream(java.lang.String arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNCharacterStream");
    }

    @Override
    public void updateNCharacterStream(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNCharacterStream");
    }

    @Override
    public void updateNClob(java.lang.String arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNClob");
    }

    @Override
    public void updateNClob(java.lang.String arg0, java.sql.NClob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNClob");
    }

    @Override
    public void updateNClob(int arg0, java.io.Reader arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNClob");
    }

    @Override
    public void updateNClob(int arg0, java.sql.NClob arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNClob");
    }

    @Override
    public void updateNClob(java.lang.String arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNClob");
    }

    @Override
    public void updateNClob(int arg0, java.io.Reader arg1, long arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNClob");
    }

    @Override
    public void updateNString(java.lang.String arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNString");
    }

    @Override
    public void updateNString(int arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNString");
    }

    @Override
    public void updateNull(java.lang.String arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNull");
    }

    @Override
    public void updateNull(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateNull");
    }

    @Override
    public void updateObject(java.lang.String arg0, java.lang.Object arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateObject");
    }

    @Override
    public void updateObject(int arg0, java.lang.Object arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateObject");
    }

    @Override
    public void updateObject(java.lang.String arg0, java.lang.Object arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateObject");
    }

    @Override
    public void updateObject(int arg0, java.lang.Object arg1, int arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateObject");
    }

    @Override
    public void updateRef(java.lang.String arg0, java.sql.Ref arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateRef");
    }

    @Override
    public void updateRef(int arg0, java.sql.Ref arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateRef");
    }

    @Override
    public void updateRow() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateRow");
    }

    @Override
    public void updateRowId(java.lang.String arg0, java.sql.RowId arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateRowId");
    }

    @Override
    public void updateRowId(int arg0, java.sql.RowId arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateRowId");
    }

    @Override
    public void updateSQLXML(java.lang.String arg0, java.sql.SQLXML arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateSQLXML");
    }

    @Override
    public void updateSQLXML(int arg0, java.sql.SQLXML arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateSQLXML");
    }

    @Override
    public void updateShort(java.lang.String arg0, short arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateShort");
    }

    @Override
    public void updateShort(int arg0, short arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateShort");
    }

    @Override
    public void updateString(java.lang.String arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateString");
    }

    @Override
    public void updateString(int arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateString");
    }

    @Override
    public void updateTime(java.lang.String arg0, java.sql.Time arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateTime");
    }

    @Override
    public void updateTime(int arg0, java.sql.Time arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateTime");
    }

    @Override
    public void updateTimestamp(java.lang.String arg0, java.sql.Timestamp arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateTimestamp");
    }

    @Override
    public void updateTimestamp(int arg0, java.sql.Timestamp arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.updateTimestamp");
    }

    @Override
    public boolean wasNull() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: ResultSet.wasNull");
    }

}

abstract class UnsupportedDatabaseMetaData implements java.sql.DatabaseMetaData {
    @Override
    public boolean allProceduresAreCallable() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.allProceduresAreCallable");
    }

    @Override
    public boolean allTablesAreSelectable() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.allTablesAreSelectable");
    }

    @Override
    public boolean autoCommitFailureClosesAllResultSets() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.autoCommitFailureClosesAllResultSets");
    }

    @Override
    public boolean dataDefinitionCausesTransactionCommit() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.dataDefinitionCausesTransactionCommit");
    }

    @Override
    public boolean dataDefinitionIgnoredInTransactions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.dataDefinitionIgnoredInTransactions");
    }

    @Override
    public boolean deletesAreDetected(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.deletesAreDetected");
    }

    @Override
    public boolean doesMaxRowSizeIncludeBlobs() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.doesMaxRowSizeIncludeBlobs");
    }

    @Override
    public boolean generatedKeyAlwaysReturned() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.generatedKeyAlwaysReturned");
    }

    @Override
    public java.sql.ResultSet getAttributes(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getAttributes");
    }

    @Override
    public java.sql.ResultSet getBestRowIdentifier(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, int arg3, boolean arg4) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getBestRowIdentifier");
    }

    @Override
    public java.lang.String getCatalogSeparator() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getCatalogSeparator");
    }

    @Override
    public java.lang.String getCatalogTerm() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getCatalogTerm");
    }

    @Override
    public java.sql.ResultSet getCatalogs() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getCatalogs");
    }

    @Override
    public java.sql.ResultSet getClientInfoProperties() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getClientInfoProperties");
    }

    @Override
    public java.sql.ResultSet getColumnPrivileges(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getColumnPrivileges");
    }

    @Override
    public java.sql.ResultSet getColumns(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getColumns");
    }

    @Override
    public java.sql.Connection getConnection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getConnection");
    }

    @Override
    public java.sql.ResultSet getCrossReference(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3, java.lang.String arg4, java.lang.String arg5) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getCrossReference");
    }

    @Override
    public int getDatabaseMajorVersion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getDatabaseMajorVersion");
    }

    @Override
    public int getDatabaseMinorVersion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getDatabaseMinorVersion");
    }

    @Override
    public java.lang.String getDatabaseProductName() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getDatabaseProductName");
    }

    @Override
    public java.lang.String getDatabaseProductVersion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getDatabaseProductVersion");
    }

    @Override
    public int getDefaultTransactionIsolation() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getDefaultTransactionIsolation");
    }

    @Override
    public int getDriverMajorVersion() {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getDriverMajorVersion");
    }

    @Override
    public int getDriverMinorVersion() {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getDriverMinorVersion");
    }

    @Override
    public java.lang.String getDriverName() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getDriverName");
    }

    @Override
    public java.lang.String getDriverVersion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getDriverVersion");
    }

    @Override
    public java.sql.ResultSet getExportedKeys(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getExportedKeys");
    }

    @Override
    public java.lang.String getExtraNameCharacters() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getExtraNameCharacters");
    }

    @Override
    public java.sql.ResultSet getFunctionColumns(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getFunctionColumns");
    }

    @Override
    public java.sql.ResultSet getFunctions(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getFunctions");
    }

    @Override
    public java.lang.String getIdentifierQuoteString() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getIdentifierQuoteString");
    }

    @Override
    public java.sql.ResultSet getImportedKeys(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getImportedKeys");
    }

    @Override
    public java.sql.ResultSet getIndexInfo(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, boolean arg3, boolean arg4) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getIndexInfo");
    }

    @Override
    public int getJDBCMajorVersion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getJDBCMajorVersion");
    }

    @Override
    public int getJDBCMinorVersion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getJDBCMinorVersion");
    }

    @Override
    public int getMaxBinaryLiteralLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxBinaryLiteralLength");
    }

    @Override
    public int getMaxCatalogNameLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxCatalogNameLength");
    }

    @Override
    public int getMaxCharLiteralLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxCharLiteralLength");
    }

    @Override
    public int getMaxColumnNameLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxColumnNameLength");
    }

    @Override
    public int getMaxColumnsInGroupBy() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxColumnsInGroupBy");
    }

    @Override
    public int getMaxColumnsInIndex() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxColumnsInIndex");
    }

    @Override
    public int getMaxColumnsInOrderBy() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxColumnsInOrderBy");
    }

    @Override
    public int getMaxColumnsInSelect() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxColumnsInSelect");
    }

    @Override
    public int getMaxColumnsInTable() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxColumnsInTable");
    }

    @Override
    public int getMaxConnections() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxConnections");
    }

    @Override
    public int getMaxCursorNameLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxCursorNameLength");
    }

    @Override
    public int getMaxIndexLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxIndexLength");
    }

    @Override
    public int getMaxProcedureNameLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxProcedureNameLength");
    }

    @Override
    public int getMaxRowSize() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxRowSize");
    }

    @Override
    public int getMaxSchemaNameLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxSchemaNameLength");
    }

    @Override
    public int getMaxStatementLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxStatementLength");
    }

    @Override
    public int getMaxStatements() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxStatements");
    }

    @Override
    public int getMaxTableNameLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxTableNameLength");
    }

    @Override
    public int getMaxTablesInSelect() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxTablesInSelect");
    }

    @Override
    public int getMaxUserNameLength() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getMaxUserNameLength");
    }

    @Override
    public java.lang.String getNumericFunctions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getNumericFunctions");
    }

    @Override
    public java.sql.ResultSet getPrimaryKeys(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getPrimaryKeys");
    }

    @Override
    public java.sql.ResultSet getProcedureColumns(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getProcedureColumns");
    }

    @Override
    public java.lang.String getProcedureTerm() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getProcedureTerm");
    }

    @Override
    public java.sql.ResultSet getProcedures(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getProcedures");
    }

    @Override
    public java.sql.ResultSet getPseudoColumns(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getPseudoColumns");
    }

    @Override
    public int getResultSetHoldability() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getResultSetHoldability");
    }

    @Override
    public java.sql.RowIdLifetime getRowIdLifetime() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getRowIdLifetime");
    }

    @Override
    public java.lang.String getSQLKeywords() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getSQLKeywords");
    }

    @Override
    public int getSQLStateType() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getSQLStateType");
    }

    @Override
    public java.lang.String getSchemaTerm() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getSchemaTerm");
    }

    @Override
    public java.sql.ResultSet getSchemas() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getSchemas");
    }

    @Override
    public java.sql.ResultSet getSchemas(java.lang.String arg0, java.lang.String arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getSchemas");
    }

    @Override
    public java.lang.String getSearchStringEscape() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getSearchStringEscape");
    }

    @Override
    public java.lang.String getStringFunctions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getStringFunctions");
    }

    @Override
    public java.sql.ResultSet getSuperTables(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getSuperTables");
    }

    @Override
    public java.sql.ResultSet getSuperTypes(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getSuperTypes");
    }

    @Override
    public java.lang.String getSystemFunctions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getSystemFunctions");
    }

    @Override
    public java.sql.ResultSet getTablePrivileges(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getTablePrivileges");
    }

    @Override
    public java.sql.ResultSet getTableTypes() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getTableTypes");
    }

    @Override
    public java.sql.ResultSet getTables(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, java.lang.String[] arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getTables");
    }

    @Override
    public java.lang.String getTimeDateFunctions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getTimeDateFunctions");
    }

    @Override
    public java.sql.ResultSet getTypeInfo() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getTypeInfo");
    }

    @Override
    public java.sql.ResultSet getUDTs(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2, int[] arg3) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getUDTs");
    }

    @Override
    public java.lang.String getURL() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getURL");
    }

    @Override
    public java.lang.String getUserName() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getUserName");
    }

    @Override
    public java.sql.ResultSet getVersionColumns(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.getVersionColumns");
    }

    @Override
    public boolean insertsAreDetected(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.insertsAreDetected");
    }

    @Override
    public boolean isCatalogAtStart() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.isCatalogAtStart");
    }

    @Override
    public boolean isReadOnly() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.isReadOnly");
    }

    @Override
    public boolean isWrapperFor(java.lang.Class<?> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.isWrapperFor");
    }

    @Override
    public boolean locatorsUpdateCopy() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.locatorsUpdateCopy");
    }

    @Override
    public boolean nullPlusNonNullIsNull() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.nullPlusNonNullIsNull");
    }

    @Override
    public boolean nullsAreSortedAtEnd() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.nullsAreSortedAtEnd");
    }

    @Override
    public boolean nullsAreSortedAtStart() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.nullsAreSortedAtStart");
    }

    @Override
    public boolean nullsAreSortedHigh() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.nullsAreSortedHigh");
    }

    @Override
    public boolean nullsAreSortedLow() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.nullsAreSortedLow");
    }

    @Override
    public boolean othersDeletesAreVisible(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.othersDeletesAreVisible");
    }

    @Override
    public boolean othersInsertsAreVisible(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.othersInsertsAreVisible");
    }

    @Override
    public boolean othersUpdatesAreVisible(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.othersUpdatesAreVisible");
    }

    @Override
    public boolean ownDeletesAreVisible(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.ownDeletesAreVisible");
    }

    @Override
    public boolean ownInsertsAreVisible(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.ownInsertsAreVisible");
    }

    @Override
    public boolean ownUpdatesAreVisible(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.ownUpdatesAreVisible");
    }

    @Override
    public boolean storesLowerCaseIdentifiers() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.storesLowerCaseIdentifiers");
    }

    @Override
    public boolean storesLowerCaseQuotedIdentifiers() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.storesLowerCaseQuotedIdentifiers");
    }

    @Override
    public boolean storesMixedCaseIdentifiers() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.storesMixedCaseIdentifiers");
    }

    @Override
    public boolean storesMixedCaseQuotedIdentifiers() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.storesMixedCaseQuotedIdentifiers");
    }

    @Override
    public boolean storesUpperCaseIdentifiers() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.storesUpperCaseIdentifiers");
    }

    @Override
    public boolean storesUpperCaseQuotedIdentifiers() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.storesUpperCaseQuotedIdentifiers");
    }

    @Override
    public boolean supportsANSI92EntryLevelSQL() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsANSI92EntryLevelSQL");
    }

    @Override
    public boolean supportsANSI92FullSQL() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsANSI92FullSQL");
    }

    @Override
    public boolean supportsANSI92IntermediateSQL() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsANSI92IntermediateSQL");
    }

    @Override
    public boolean supportsAlterTableWithAddColumn() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsAlterTableWithAddColumn");
    }

    @Override
    public boolean supportsAlterTableWithDropColumn() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsAlterTableWithDropColumn");
    }

    @Override
    public boolean supportsBatchUpdates() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsBatchUpdates");
    }

    @Override
    public boolean supportsCatalogsInDataManipulation() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsCatalogsInDataManipulation");
    }

    @Override
    public boolean supportsCatalogsInIndexDefinitions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsCatalogsInIndexDefinitions");
    }

    @Override
    public boolean supportsCatalogsInPrivilegeDefinitions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsCatalogsInPrivilegeDefinitions");
    }

    @Override
    public boolean supportsCatalogsInProcedureCalls() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsCatalogsInProcedureCalls");
    }

    @Override
    public boolean supportsCatalogsInTableDefinitions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsCatalogsInTableDefinitions");
    }

    @Override
    public boolean supportsColumnAliasing() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsColumnAliasing");
    }

    @Override
    public boolean supportsConvert() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsConvert");
    }

    @Override
    public boolean supportsConvert(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsConvert");
    }

    @Override
    public boolean supportsCoreSQLGrammar() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsCoreSQLGrammar");
    }

    @Override
    public boolean supportsCorrelatedSubqueries() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsCorrelatedSubqueries");
    }

    @Override
    public boolean supportsDataDefinitionAndDataManipulationTransactions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsDataDefinitionAndDataManipulationTransactions");
    }

    @Override
    public boolean supportsDataManipulationTransactionsOnly() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsDataManipulationTransactionsOnly");
    }

    @Override
    public boolean supportsDifferentTableCorrelationNames() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsDifferentTableCorrelationNames");
    }

    @Override
    public boolean supportsExpressionsInOrderBy() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsExpressionsInOrderBy");
    }

    @Override
    public boolean supportsExtendedSQLGrammar() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsExtendedSQLGrammar");
    }

    @Override
    public boolean supportsFullOuterJoins() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsFullOuterJoins");
    }

    @Override
    public boolean supportsGetGeneratedKeys() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsGetGeneratedKeys");
    }

    @Override
    public boolean supportsGroupBy() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsGroupBy");
    }

    @Override
    public boolean supportsGroupByBeyondSelect() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsGroupByBeyondSelect");
    }

    @Override
    public boolean supportsGroupByUnrelated() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsGroupByUnrelated");
    }

    @Override
    public boolean supportsIntegrityEnhancementFacility() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsIntegrityEnhancementFacility");
    }

    @Override
    public boolean supportsLikeEscapeClause() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsLikeEscapeClause");
    }

    @Override
    public boolean supportsLimitedOuterJoins() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsLimitedOuterJoins");
    }

    @Override
    public boolean supportsMinimumSQLGrammar() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsMinimumSQLGrammar");
    }

    @Override
    public boolean supportsMixedCaseIdentifiers() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsMixedCaseIdentifiers");
    }

    @Override
    public boolean supportsMixedCaseQuotedIdentifiers() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsMixedCaseQuotedIdentifiers");
    }

    @Override
    public boolean supportsMultipleOpenResults() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsMultipleOpenResults");
    }

    @Override
    public boolean supportsMultipleResultSets() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsMultipleResultSets");
    }

    @Override
    public boolean supportsMultipleTransactions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsMultipleTransactions");
    }

    @Override
    public boolean supportsNamedParameters() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsNamedParameters");
    }

    @Override
    public boolean supportsNonNullableColumns() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsNonNullableColumns");
    }

    @Override
    public boolean supportsOpenCursorsAcrossCommit() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsOpenCursorsAcrossCommit");
    }

    @Override
    public boolean supportsOpenCursorsAcrossRollback() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsOpenCursorsAcrossRollback");
    }

    @Override
    public boolean supportsOpenStatementsAcrossCommit() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsOpenStatementsAcrossCommit");
    }

    @Override
    public boolean supportsOpenStatementsAcrossRollback() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsOpenStatementsAcrossRollback");
    }

    @Override
    public boolean supportsOrderByUnrelated() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsOrderByUnrelated");
    }

    @Override
    public boolean supportsOuterJoins() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsOuterJoins");
    }

    @Override
    public boolean supportsPositionedDelete() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsPositionedDelete");
    }

    @Override
    public boolean supportsPositionedUpdate() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsPositionedUpdate");
    }

    @Override
    public boolean supportsResultSetConcurrency(int arg0, int arg1) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsResultSetConcurrency");
    }

    @Override
    public boolean supportsResultSetHoldability(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsResultSetHoldability");
    }

    @Override
    public boolean supportsResultSetType(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsResultSetType");
    }

    @Override
    public boolean supportsSavepoints() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSavepoints");
    }

    @Override
    public boolean supportsSchemasInDataManipulation() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSchemasInDataManipulation");
    }

    @Override
    public boolean supportsSchemasInIndexDefinitions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSchemasInIndexDefinitions");
    }

    @Override
    public boolean supportsSchemasInPrivilegeDefinitions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSchemasInPrivilegeDefinitions");
    }

    @Override
    public boolean supportsSchemasInProcedureCalls() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSchemasInProcedureCalls");
    }

    @Override
    public boolean supportsSchemasInTableDefinitions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSchemasInTableDefinitions");
    }

    @Override
    public boolean supportsSelectForUpdate() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSelectForUpdate");
    }

    @Override
    public boolean supportsStatementPooling() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsStatementPooling");
    }

    @Override
    public boolean supportsStoredFunctionsUsingCallSyntax() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsStoredFunctionsUsingCallSyntax");
    }

    @Override
    public boolean supportsStoredProcedures() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsStoredProcedures");
    }

    @Override
    public boolean supportsSubqueriesInComparisons() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSubqueriesInComparisons");
    }

    @Override
    public boolean supportsSubqueriesInExists() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSubqueriesInExists");
    }

    @Override
    public boolean supportsSubqueriesInIns() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSubqueriesInIns");
    }

    @Override
    public boolean supportsSubqueriesInQuantifieds() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsSubqueriesInQuantifieds");
    }

    @Override
    public boolean supportsTableCorrelationNames() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsTableCorrelationNames");
    }

    @Override
    public boolean supportsTransactionIsolationLevel(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsTransactionIsolationLevel");
    }

    @Override
    public boolean supportsTransactions() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsTransactions");
    }

    @Override
    public boolean supportsUnion() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsUnion");
    }

    @Override
    public boolean supportsUnionAll() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.supportsUnionAll");
    }

    @Override
    public <T> T unwrap(java.lang.Class<T> arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.unwrap");
    }

    @Override
    public boolean updatesAreDetected(int arg0) throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.updatesAreDetected");
    }

    @Override
    public boolean usesLocalFilePerTable() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.usesLocalFilePerTable");
    }

    @Override
    public boolean usesLocalFiles() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: DatabaseMetaData.usesLocalFiles");
    }

}

abstract class UnsupportedXAConnection implements javax.sql.XAConnection {
    @Override
    public void addConnectionEventListener(javax.sql.ConnectionEventListener arg0) {
        throw new UnsupportedOperationException("Not implemented: XAConnection.addConnectionEventListener");
    }

    @Override
    public void addStatementEventListener(javax.sql.StatementEventListener arg0) {
        throw new UnsupportedOperationException("Not implemented: XAConnection.addStatementEventListener");
    }

    @Override
    public void close() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: XAConnection.close");
    }

    @Override
    public java.sql.Connection getConnection() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: XAConnection.getConnection");
    }

    @Override
    public javax.transaction.xa.XAResource getXAResource() throws java.sql.SQLException {
        throw new UnsupportedOperationException("Not implemented: XAConnection.getXAResource");
    }

    @Override
    public void removeConnectionEventListener(javax.sql.ConnectionEventListener arg0) {
        throw new UnsupportedOperationException("Not implemented: XAConnection.removeConnectionEventListener");
    }

    @Override
    public void removeStatementEventListener(javax.sql.StatementEventListener arg0) {
        throw new UnsupportedOperationException("Not implemented: XAConnection.removeStatementEventListener");
    }

}

abstract class UnsupportedXAResource implements javax.transaction.xa.XAResource {
    @Override
    public void commit(javax.transaction.xa.Xid arg0, boolean arg1) throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.commit");
    }

    @Override
    public void end(javax.transaction.xa.Xid arg0, int arg1) throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.end");
    }

    @Override
    public void forget(javax.transaction.xa.Xid arg0) throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.forget");
    }

    @Override
    public int getTransactionTimeout() throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.getTransactionTimeout");
    }

    @Override
    public boolean isSameRM(javax.transaction.xa.XAResource arg0) throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.isSameRM");
    }

    @Override
    public int prepare(javax.transaction.xa.Xid arg0) throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.prepare");
    }

    @Override
    public javax.transaction.xa.Xid[] recover(int arg0) throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.recover");
    }

    @Override
    public void rollback(javax.transaction.xa.Xid arg0) throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.rollback");
    }

    @Override
    public boolean setTransactionTimeout(int arg0) throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.setTransactionTimeout");
    }

    @Override
    public void start(javax.transaction.xa.Xid arg0, int arg1) throws javax.transaction.xa.XAException {
        throw new UnsupportedOperationException("Not implemented: XAResource.start");
    }

}

final class FakeDataSource extends UnsupportedDataSource {
    final List<FakeConnection> openedConnections = new ArrayList<>();
    String lastUser;
    String lastPassword;
    int loginTimeout;
    PrintWriter logWriter;
    @Override public java.sql.Connection getConnection() { return open(null, null); }
    @Override public java.sql.Connection getConnection(String username, String password) { return open(username, password); }
    private FakeConnection open(String username, String password) { lastUser = username; lastPassword = password; FakeConnection c = new FakeConnection(); openedConnections.add(c); return c; }
    @Override public <T> T unwrap(Class<T> iface) throws SQLException { if (iface.isInstance(this)) return iface.cast(this); throw new SQLException("Unsupported unwrap: " + iface.getName()); }
    @Override public boolean isWrapperFor(Class<?> iface) { return iface.isInstance(this); }
    @Override public PrintWriter getLogWriter() { return logWriter; }
    @Override public void setLogWriter(PrintWriter out) { logWriter = out; }
    @Override public void setLoginTimeout(int seconds) { loginTimeout = seconds; }
    @Override public int getLoginTimeout() { return loginTimeout; }
    @Override public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException { return java.util.logging.Logger.getGlobal(); }
}

final class FakeConnection extends UnsupportedConnection {
    boolean closed;
    int closeCount;
    String failingSql;
    final FakeDatabaseMetaData databaseMetaData = new FakeDatabaseMetaData(this);
    @Override public java.sql.Statement createStatement() { return new FakeStatement(this); }
    @Override public java.sql.Statement createStatement(int a, int b) { return createStatement(); }
    @Override public java.sql.Statement createStatement(int a, int b, int c) { return createStatement(); }
    @Override public java.sql.PreparedStatement prepareStatement(String sql) { return new FakePreparedStatement(this, sql); }
    @Override public java.sql.PreparedStatement prepareStatement(String sql, int a) { return prepareStatement(sql); }
    @Override public java.sql.PreparedStatement prepareStatement(String sql, int[] a) { return prepareStatement(sql); }
    @Override public java.sql.PreparedStatement prepareStatement(String sql, String[] a) { return prepareStatement(sql); }
    @Override public java.sql.PreparedStatement prepareStatement(String sql, int a, int b) { return prepareStatement(sql); }
    @Override public java.sql.PreparedStatement prepareStatement(String sql, int a, int b, int c) { return prepareStatement(sql); }
    @Override public java.sql.CallableStatement prepareCall(String sql) { return new FakeCallableStatement(this, sql); }
    @Override public java.sql.CallableStatement prepareCall(String sql, int a, int b) { return prepareCall(sql); }
    @Override public java.sql.CallableStatement prepareCall(String sql, int a, int b, int c) { return prepareCall(sql); }
    @Override public java.sql.DatabaseMetaData getMetaData() { return databaseMetaData; }
    @Override public void close() { closed = true; closeCount++; }
    @Override public boolean isClosed() { return closed; }
    @Override public boolean isValid(int timeout) { return !closed; }
    @Override public void commit() { }
    @Override public void rollback() { }
    @Override public void rollback(java.sql.Savepoint savepoint) { }
    @Override public boolean getAutoCommit() { return true; }
    @Override public void setAutoCommit(boolean autoCommit) { }
    @Override public java.sql.SQLWarning getWarnings() { return null; }
    @Override public void clearWarnings() { }
    @Override public String nativeSQL(String sql) { return sql; }
    @Override public java.util.Map<String, Class<?>> getTypeMap() { return java.util.Map.of(); }
    @Override public void setTypeMap(Map<String, Class<?>> map) { }
    @Override public int getTransactionIsolation() { return java.sql.Connection.TRANSACTION_NONE; }
    @Override public void setTransactionIsolation(int level) { }
    @Override public void abort(Executor executor) { close(); }
    @Override public int getNetworkTimeout() { return 0; }
    @Override public void setNetworkTimeout(Executor executor, int milliseconds) { }
    @Override public String getSchema() { return "default"; }
    @Override public void setSchema(String schema) { }
    @Override public <T> T unwrap(Class<T> iface) throws SQLException { if (iface.isInstance(this)) return iface.cast(this); throw new SQLException("Unsupported unwrap: " + iface.getName()); }
    @Override public boolean isWrapperFor(Class<?> iface) { return iface.isInstance(this); }
}

class FakeStatement extends UnsupportedStatement {
    final FakeConnection connection;
    boolean closed;
    int closeCount;
    FakeResultSet currentResultSet;
    FakeStatement(FakeConnection connection) { this.connection = connection; }
    @Override public java.sql.ResultSet executeQuery(String sql) throws SQLException { if (sql != null && sql.equals(connection.failingSql)) throw new SQLException("provider boom"); currentResultSet = new FakeResultSet(this, sql == null ? "result" : sql); return currentResultSet; }
    @Override public int executeUpdate(String sql) { return 1; }
    @Override public boolean execute(String sql) { return true; }
    @Override public java.sql.ResultSet getResultSet() { if (currentResultSet == null) currentResultSet = new FakeResultSet(this, "current"); return currentResultSet; }
    @Override public java.sql.ResultSet getGeneratedKeys() { return new FakeResultSet(this, "generated"); }
    @Override public java.sql.Connection getConnection() { return connection; }
    @Override public void close() { closed = true; closeCount++; }
    @Override public boolean isClosed() { return closed; }
    @Override public java.sql.SQLWarning getWarnings() { return null; }
    @Override public void clearWarnings() { }
    @Override public boolean getMoreResults() { return false; }
    @Override public boolean getMoreResults(int current) { return false; }
    @Override public int getResultSetConcurrency() { return java.sql.ResultSet.CONCUR_READ_ONLY; }
    @Override public int getResultSetType() { return java.sql.ResultSet.TYPE_FORWARD_ONLY; }
    @Override public int getResultSetHoldability() { return java.sql.ResultSet.HOLD_CURSORS_OVER_COMMIT; }
    @Override public int getUpdateCount() { return 1; }
    @Override public void closeOnCompletion() { }
    @Override public boolean isCloseOnCompletion() { return false; }
    @Override public int getFetchDirection() { return java.sql.ResultSet.FETCH_FORWARD; }
    @Override public void setFetchDirection(int direction) { }
    @Override public int getFetchSize() { return 0; }
    @Override public void setFetchSize(int rows) { }
    @Override public int getMaxRows() { return 0; }
    @Override public void setMaxRows(int max) { }
    @Override public int getMaxFieldSize() { return 0; }
    @Override public void setMaxFieldSize(int max) { }
    @Override public int getQueryTimeout() { return 0; }
    @Override public void setQueryTimeout(int seconds) { }
    @Override public void cancel() { }
    @Override public void clearBatch() { }
    @Override public int[] executeBatch() { return new int[0]; }
    @Override public long[] executeLargeBatch() { return new long[0]; }
    @Override public long executeLargeUpdate(String sql) { return 1L; }
    @Override public long getLargeUpdateCount() { return 0L; }
    @Override public void setLargeMaxRows(long max) { }
    @Override public long getLargeMaxRows() { return 0L; }
    @Override public boolean isPoolable() { return false; }
    @Override public void setPoolable(boolean poolable) { }
    @Override public <T> T unwrap(Class<T> iface) throws SQLException { if (iface.isInstance(this)) return iface.cast(this); throw new SQLException("Unsupported unwrap: " + iface.getName()); }
    @Override public boolean isWrapperFor(Class<?> iface) { return iface.isInstance(this); }
}

class FakePreparedStatement extends UnsupportedPreparedStatement {
    final FakeConnection connection;
    final String sql;
    boolean closed;
    int closeCount;
    FakePreparedStatement(FakeConnection connection, String sql) { this.connection = connection; this.sql = sql; }
    @Override public java.sql.ResultSet executeQuery() throws SQLException { return new FakeResultSet(this, sql); }
    @Override public int executeUpdate() throws SQLException { return 1; }
    @Override public boolean execute() throws SQLException { return true; }
    @Override public java.sql.Connection getConnection() { return connection; }
    @Override public void close() { closed = true; closeCount++; }
    @Override public boolean isClosed() { return closed; }
    @Override public void clearParameters() { }
    @Override public void setObject(int parameterIndex, Object x) { }
    @Override public void setString(int parameterIndex, String x) { }
    @Override public java.sql.ResultSet getGeneratedKeys() { return new FakeResultSet(this, "generated"); }
    @Override public <T> T unwrap(Class<T> iface) throws SQLException { if (iface.isInstance(this)) return iface.cast(this); throw new SQLException("Unsupported unwrap: " + iface.getName()); }
    @Override public boolean isWrapperFor(Class<?> iface) { return iface.isInstance(this); }
}

final class FakeCallableStatement extends UnsupportedCallableStatement {
    final FakeConnection connection;
    final String sql;
    boolean closed;
    int closeCount;
    FakeCallableStatement(FakeConnection connection, String sql) { this.connection = connection; this.sql = sql; }
    @Override public java.sql.ResultSet executeQuery() throws SQLException { return new FakeResultSet(this, sql); }
    @Override public int executeUpdate() throws SQLException { return 1; }
    @Override public boolean execute() throws SQLException { return true; }
    @Override public java.sql.Connection getConnection() { return connection; }
    @Override public void close() { closed = true; closeCount++; }
    @Override public boolean isClosed() { return closed; }
    @Override public void clearParameters() { }
    @Override public void setObject(int parameterIndex, Object x) { }
    @Override public void setString(int parameterIndex, String x) { }
    @Override public java.sql.ResultSet getGeneratedKeys() { return new FakeResultSet(this, "generated"); }
    @Override public <T> T unwrap(Class<T> iface) throws SQLException { if (iface.isInstance(this)) return iface.cast(this); throw new SQLException("Unsupported unwrap: " + iface.getName()); }
    @Override public boolean isWrapperFor(Class<?> iface) { return iface.isInstance(this); }
}

final class FakeResultSet extends UnsupportedResultSet {
    final java.sql.Statement statement;
    final String value;
    boolean closed;
    int closeCount;
    int index = -1;
    FakeResultSet(java.sql.Statement statement, String value) { this.statement = statement; this.value = value; }
    @Override public boolean next() { return ++index == 0; }
    @Override public void close() { closed = true; closeCount++; }
    @Override public boolean isClosed() { return closed; }
    @Override public String getString(int columnIndex) { return value; }
    @Override public String getString(String columnLabel) { return value; }
    @Override public java.sql.Statement getStatement() { return statement; }
    @Override public int getType() { return java.sql.ResultSet.TYPE_FORWARD_ONLY; }
    @Override public int getConcurrency() { return java.sql.ResultSet.CONCUR_READ_ONLY; }
    @Override public int getFetchDirection() { return java.sql.ResultSet.FETCH_FORWARD; }
    @Override public void setFetchDirection(int direction) { }
    @Override public int getFetchSize() { return 0; }
    @Override public void setFetchSize(int rows) { }
    @Override public int getRow() { return Math.max(index + 1, 0); }
    @Override public boolean wasNull() { return false; }
    @Override public <T> T unwrap(Class<T> iface) throws SQLException { if (iface.isInstance(this)) return iface.cast(this); throw new SQLException("Unsupported unwrap: " + iface.getName()); }
    @Override public boolean isWrapperFor(Class<?> iface) { return iface.isInstance(this); }
}

final class FakeDatabaseMetaData extends UnsupportedDatabaseMetaData {
    final java.sql.Connection connection;
    FakeDatabaseMetaData(java.sql.Connection connection) { this.connection = connection; }
    @Override public java.sql.Connection getConnection() { return connection; }
    @Override public String getURL() { return "jdbc:fake"; }
    @Override public String getDatabaseProductName() { return "fake-db"; }
    @Override public String getDriverName() { return "fake-driver"; }
    @Override public boolean allProceduresAreCallable() { return true; }
    @Override public boolean allTablesAreSelectable() { return true; }
    @Override public <T> T unwrap(Class<T> iface) throws SQLException { if (iface.isInstance(this)) return iface.cast(this); throw new SQLException("Unsupported unwrap: " + iface.getName()); }
    @Override public boolean isWrapperFor(Class<?> iface) { return iface.isInstance(this); }
}

final class FakeXADataSource extends UnsupportedXADataSource {
    final List<FakeXAConnection> openedConnections = new ArrayList<>();
    String lastUser;
    String lastPassword;
    int loginTimeout;
    PrintWriter logWriter;
    @Override public javax.sql.XAConnection getXAConnection() { return open(null, null); }
    @Override public javax.sql.XAConnection getXAConnection(String user, String password) { return open(user, password); }
    private FakeXAConnection open(String user, String password) { lastUser = user; lastPassword = password; FakeXAConnection c = new FakeXAConnection(); openedConnections.add(c); return c; }
    @Override public PrintWriter getLogWriter() { return logWriter; }
    @Override public void setLogWriter(PrintWriter out) { logWriter = out; }
    @Override public void setLoginTimeout(int seconds) { loginTimeout = seconds; }
    @Override public int getLoginTimeout() { return loginTimeout; }
    @Override public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException { return java.util.logging.Logger.getGlobal(); }
}

final class FakeXAConnection extends UnsupportedXAConnection {
    final FakeConnection connection = new FakeConnection();
    final FakeXAResource xaResource = new FakeXAResource(UUID.randomUUID().toString());
    final List<ConnectionEventListener> connectionEventListeners = new ArrayList<>();
    final List<StatementEventListener> statementEventListeners = new ArrayList<>();
    boolean closed;
    int closeCount;
    @Override public java.sql.Connection getConnection() { return connection; }
    @Override public XAResource getXAResource() { return xaResource; }
    @Override public void close() { closed = true; closeCount++; }
    @Override public void addConnectionEventListener(ConnectionEventListener listener) { connectionEventListeners.add(listener); }
    @Override public void removeConnectionEventListener(ConnectionEventListener listener) { connectionEventListeners.remove(listener); }
    @Override public void addStatementEventListener(StatementEventListener listener) { statementEventListeners.add(listener); }
    @Override public void removeStatementEventListener(StatementEventListener listener) { statementEventListeners.remove(listener); }
}

final class FakeXAResource extends UnsupportedXAResource {
    final String resourceManagerId;
    int timeoutSeconds;
    FakeXAResource(String resourceManagerId) { this.resourceManagerId = resourceManagerId; }
    @Override public void commit(Xid xid, boolean onePhase) { }
    @Override public void end(Xid xid, int flags) { }
    @Override public void forget(Xid xid) { }
    @Override public int getTransactionTimeout() { return timeoutSeconds; }
    @Override public boolean isSameRM(XAResource xares) { return xares instanceof FakeXAResource other && resourceManagerId.equals(other.resourceManagerId); }
    @Override public int prepare(Xid xid) { return XAResource.XA_OK; }
    @Override public Xid[] recover(int flag) { return new Xid[0]; }
    @Override public void rollback(Xid xid) { }
    @Override public boolean setTransactionTimeout(int seconds) { timeoutSeconds = seconds; return true; }
    @Override public void start(Xid xid, int flags) { }
}
