package ar.edu.ubp.das.mvc.db;

import ar.edu.ubp.das.mvc.config.DatasourceConfig;
import ar.edu.ubp.das.mvc.db.annotations.Column;
import ar.edu.ubp.das.mvc.db.annotations.Entity;
import ar.edu.ubp.das.mvc.db.annotations.NoEntityException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public abstract class DaoImpl<T> implements Dao<T> {

    private Class<T> clazz;
    private DatasourceConfig datasource;
    private Connection connection;
    private CallableStatement statement;

    public DaoImpl(final Class<T> clazz) {
        this.clazz = clazz;
    }

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

    // This could return Optional<T>
    private T mapToObject(final ResultSet rs) {
        assert rs != null;
        T bean = null;
        try {
            if (!this.clazz.isAnnotationPresent(Entity.class)) {
                throw new NoEntityException();
            } else {
                final ResultSetMetaData rsmd = rs.getMetaData(); // get the resultset metadata
                final Field[] attributes = this.clazz.getDeclaredFields(); // get all the attributes of Class clazz

                bean = this.clazz.newInstance();

                for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {

                    final String columnName = rsmd.getColumnName(_iterator + 1); // get the SQL column name
                    final Object columnValue = rs.getObject(_iterator + 1); // get the SQL column value

                    /*
					System.out.println("ColumnName: " + columnName
							+ "\tColumnType: " + rsmd.getColumnTypeName(_iterator + 1)
					+ "\tColumnValue: " + rs.getObject(_iterator + 1));
					*/

                    // iterating over clazz attributes to check
                    for (final Field attribute : attributes) {
                        // if any attribute has 'Column' annotation with matching 'name' value
                        if (attribute.isAnnotationPresent(Column.class)) {
                            final Column column = attribute.getAnnotation(Column.class); // get @Column for field
                            if (column.name().equalsIgnoreCase(columnName)) { // missing check: columnValue != null
                                final String fieldName = attribute.getName();
                                // get field from class for given filedName
                                final Field field = bean.getClass().getDeclaredField(fieldName);
                                field.setAccessible(true); // in case the field is private
                                field.set(bean, columnValue); // bean.field = columnValue
                                break;
                            }
                        }
                    } // EndOf for(Field field : fields)
                } // EndOf for(_iterator...)
            }
        } catch (final IllegalAccessException | SQLException | InstantiationException | NoEntityException
                | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return bean;
    }

    private void setParameters(final T bean, final String... columnNames) throws SQLException {
        int inputParameter = 1;
        for (final String columnName : columnNames) {
            try {
                final Field field = bean.getClass().getDeclaredField(columnName);
                field.setAccessible(true); // in case the field is private
                this.setParameter(inputParameter, field.get(bean));
            } catch (final NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
            inputParameter++;
        }
    }

    public void executeProcedure(final String procedureName, final T bean, final String... columnNames) throws SQLException {
        this.connect();
        this.setProcedure(procedureName);
        setParameters(bean, columnNames);
        this.executeUpdate();
        this.disconnect();
    }

    public List<T> executeQueryProcedure(final String procedureName, final T bean, final String... columnNames) throws SQLException {
        this.connect();
        this.setProcedure(procedureName, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        setParameters(bean, columnNames);
        final List<T> forms = this.executeQuery();
        this.disconnect();
        return forms;
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

    public List<T> executeQuery() throws SQLException {
        final List<T> list = new LinkedList<>();
        final ResultSet result = this.statement.executeQuery();
        while (result.next()) {
            list.add(this.mapToObject(result));
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

    public void setParameter(final int paramIndex, final Object object) throws SQLException {
        if (object instanceof Float) {
            this.statement.setFloat(paramIndex, (Float) object);
        } else if (object instanceof Double) {
            this.statement.setDouble(paramIndex, (Double) object);
        } else if (object instanceof Short) {
            this.statement.setShort(paramIndex, (Short) object);
        } else if (object instanceof Integer) {
            this.statement.setInt(paramIndex, (Integer) object);
        } else if (object instanceof Long) {
            this.statement.setLong(paramIndex, (Long) object);
        } else if (object instanceof String) {
            this.statement.setString(paramIndex, (String) object);
        } else if (object instanceof Date) {
            this.statement.setDate(paramIndex, (Date) object);
        } else if (object instanceof Timestamp) {
            this.statement.setTimestamp(paramIndex, (Timestamp) object);
        }
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
