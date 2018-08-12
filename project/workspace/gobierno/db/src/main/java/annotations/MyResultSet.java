package annotations;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MyResultSet<T> {

    private final ResultSet rs;
    private final Class<T> clazz;

    public MyResultSet(final ResultSet rs, final Class<T> clazz) {
        assert rs != null;
        assert clazz != null;
        this.rs = rs;
        this.clazz = clazz;
    }

    // This could return Optional<T>
    public T mapToObject() {
        T bean = null;
        try {
            if (!this.clazz.isAnnotationPresent(Entity.class)) {
                throw new NoEntityException();
            } else {
                final ResultSetMetaData rsmd = this.rs.getMetaData(); // get the resultset metadata
                final Field[] attributes = this.clazz.getDeclaredFields(); // get all the attributes of Class clazz

                bean = (T) this.clazz.newInstance();

                for (int _iterator = 0; _iterator < rsmd.getColumnCount(); _iterator++) {

                    final String columnName = rsmd.getColumnName(_iterator + 1); // get the SQL column name
                    final Object columnValue = this.rs.getObject(_iterator + 1); // get the SQL column value

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

    public List<T> mapToObjectList() {
        List<T> outputList = null;
        try {
            outputList = new ArrayList<>();

            while (this.rs.next()) {
                final T bean = (T) mapToObject();
                outputList.add(bean);
            }

        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return outputList;
    }

    public T mapToSingleObject() {
        T bean = null;
        try {
            if (this.rs.next()) {
                bean = (T) mapToObject();
            }

        } catch (final SQLException e) {
            e.printStackTrace();
        }
        return bean;
    }


}
