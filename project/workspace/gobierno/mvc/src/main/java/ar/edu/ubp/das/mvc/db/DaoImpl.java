package ar.edu.ubp.das.mvc.db;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.mvc.config.DatasourceConfig;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public abstract class DaoImpl implements Dao {

    private DatasourceConfig datasource;
    private Connection connection;
    private CallableStatement statement;

    @Override
    protected void finalize() throws Throwable {
        try {
            if (this.statement != null && !this.statement.isClosed()) {
                this.statement.close();
            }
        } catch (final SQLException ex) {
            throw new Throwable(ex.getMessage());
        } finally {
            try {
                if (this.connection != null && !this.connection.isClosed()) {
                    this.connection.close();
                }
            } catch (final SQLException ex) {
                throw new Throwable(ex.getMessage());
            } finally {
                super.finalize();
            }
        }
    }

    public void connect() throws SQLException {
        try {
            Class.forName(this.datasource.getDriver()).newInstance();
            this.connection = DriverManager.getConnection(this.datasource.getUrl(), this.datasource.getUsername(), this.datasource.getPassword());
            this.connection.setAutoCommit(true);
        } catch (final InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
            throw new SQLException(ex.getMessage());
        } catch (final SQLException ex) {
            throw new SQLException("TEXT.LOGINDATA_NULO");
        }
    }

    public void disconnect() throws SQLException {
        this.statement.close();
        this.connection.close();
    }

    public int executeUpdate() throws SQLException {
        int rows = 0;
        try {
            this.connection.setAutoCommit(false);
            rows = this.statement.executeUpdate();
            this.connection.commit();
        } catch (final SQLException ex) {
            this.connection.rollback();
            throw new SQLException(ex.getMessage());
        } finally {
            this.connection.setAutoCommit(true);
        }
        return rows;
    }

    public List<DynaActionForm> executeQuery() throws SQLException {
        final List<DynaActionForm> list = new LinkedList<>();
        final ResultSet result = this.statement.executeQuery();
        while (result.next()) {
            list.add(this.make(result));
        }
        return list;
    }

    public void setDatasource(final DatasourceConfig datasource) {
        this.datasource = datasource;
    }

    public void setProcedure(final String procedure) throws SQLException {
        this.statement = this.connection.prepareCall("{ CALL " + procedure + " }");
    }

    public void setProcedure(final String procedure, final int resultSetType, final int resultSetConcurrency) throws SQLException {
        this.statement = this.connection.prepareCall("{ CALL " + procedure + " }", resultSetType, resultSetConcurrency);
    }

    public void setNull(final int paramIndex, final int sqlType) throws SQLException {
        this.statement.setNull(paramIndex, sqlType);
    }

    public void setParameter(final int paramIndex, final long paramValue) throws SQLException {
        this.statement.setLong(paramIndex, paramValue);
    }

    public void setParameter(final int paramIndex, final int paramValue) throws SQLException {
        this.statement.setInt(paramIndex, paramValue);
    }

    public void setParameter(final int paramIndex, final short paramValue) throws SQLException {
        this.statement.setShort(paramIndex, paramValue);
    }

    public void setParameter(final int paramIndex, final double paramValue) throws SQLException {
        this.statement.setDouble(paramIndex, paramValue);
    }

    public void setParameter(final int paramIndex, final float paramValue) throws SQLException {
        this.statement.setFloat(paramIndex, paramValue);
    }

    public void setParameter(final int paramIndex, final String paramValue) throws SQLException {
        this.statement.setString(paramIndex, paramValue);
    }

    public void setParameter(final int paramIndex, final Date paramValue) throws SQLException {
        this.statement.setDate(paramIndex, paramValue);
    }

    public void setParameter(final int paramIndex, final Timestamp paramValue) throws SQLException {
        this.statement.setTimestamp(paramIndex, paramValue);
    }

    public void setOutParameter(final int paramIndex, final int sqlType) throws SQLException {
        this.statement.registerOutParameter(paramIndex, sqlType);
    }

    public CallableStatement getStatement() {
        return this.statement;
    }

    public long getLongParam(final String paramName) throws SQLException {
        return this.statement.getLong(paramName);
    }

    public int getIntParam(final String paramName) throws SQLException {
        return this.statement.getInt(paramName);
    }

    public short getShortParam(final String paramName) throws SQLException {
        return this.statement.getShort(paramName);
    }

    public double getDoubleParam(final String paramName) throws SQLException {
        return this.statement.getDouble(paramName);
    }

    public double getFloatParam(final String paramName) throws SQLException {
        return this.statement.getFloat(paramName);
    }

    public String getStringParam(final String paramName) throws SQLException {
        return this.statement.getString(paramName);
    }

    public Date getDateParam(final String paramName) throws SQLException {
        return this.statement.getDate(paramName);
    }

    public long getLongParam(final int paramIndex) throws SQLException {
        return this.statement.getLong(paramIndex);
    }

    public int getIntParam(final int paramIndex) throws SQLException {
        return this.statement.getInt(paramIndex);
    }

    public short getShortParam(final int paramIndex) throws SQLException {
        return this.statement.getShort(paramIndex);
    }

    public double getDoubleParam(final int paramIndex) throws SQLException {
        return this.statement.getDouble(paramIndex);
    }

    public double getFloatParam(final int paramIndex) throws SQLException {
        return this.statement.getFloat(paramIndex);
    }

    public String getStringParam(final int paramIndex) throws SQLException {
        return this.statement.getString(paramIndex);
    }

    public Date getDateParam(final int paramIndex) throws SQLException {
        return this.statement.getDate(paramIndex);
    }

}
