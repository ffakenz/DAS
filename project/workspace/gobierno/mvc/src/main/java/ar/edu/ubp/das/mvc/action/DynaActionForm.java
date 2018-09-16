package ar.edu.ubp.das.mvc.action;

import ar.edu.ubp.das.mvc.db.annotations.Entity;
import ar.edu.ubp.das.mvc.db.annotations.NoEntityException;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class DynaActionForm {

    private String name;
    private Map<String, Object> items;

    public DynaActionForm() {
        this.name = this.getClass().getName();
        this.items = new HashMap<>();
    }

    public void setItem(final String name, final String value) {
        this.items.put(name, value);
    }

    public void setItem(final String name, final String[] value) {
        this.items.put(name, value);
    }

    public void removeItem(final String name) {
        this.items.remove(name);
    }

    public int size() {
        return this.items.size();
    }

    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Optional<String> getItem(final String name) {
        if (this.items.containsKey(name)) {
            return Optional.of(String.valueOf(this.items.get(name)));
        }
        return Optional.empty();
    }

    public <T> T convertTo(final Class<T> clazz) {
        T bean = null;
        try {
            if (!clazz.isAnnotationPresent(Entity.class)) {
                throw new NoEntityException();
            } else {
                final Field[] attributes = clazz.getDeclaredFields(); // get all the attributes of Class clazz

                bean = clazz.newInstance();

                for (final Field attribute : attributes) {
                    // if we got an item with same attribute name
                    if (this.getItem(attribute.getName()).isPresent()) {
                        final String fieldName = attribute.getName();
                        // get field from class for given filedName
                        final Field field = bean.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true); // in case the field is private
                        final Class<?> fieldType = field.getType();
                        final String itemValue = this.getItem(attribute.getName()).get();
                        field.set(bean, fromStringTo(fieldType, itemValue)); // bean.field = columnValue // CHECK GET ??
                    }
                } // EndOf for(Field field : fields)
            }
        } catch (final IllegalAccessException | InstantiationException | NoEntityException
                | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return bean;
    }

    private <T> Object fromStringTo(final Class<T> fieldType, final String str) {
        if (fieldType.getTypeName().equals("java.lang.Float")) {
            return Float.valueOf(str);
        } else if (fieldType.getTypeName().equals("java.lang.Double")) {
            return Double.valueOf(str);
        } else if (fieldType.getTypeName().equals("java.lang.Short")) {
            return Short.valueOf(str);
        } else if (fieldType.getTypeName().equals("java.lang.Integer")) {
            return Integer.valueOf(str);
        } else if (fieldType.getTypeName().equals("java.lang.Long")) {
            return Long.valueOf(str);
        } else if (fieldType.getTypeName().equals("java.lang.String")) {
            return String.valueOf(str);
        } else if (fieldType.getTypeName().equals("java.lang.Date")) {
            return Date.valueOf(str);
        } else if (fieldType.getTypeName().equals("java.lang.Timestamp")) {
            return Timestamp.valueOf(str);
        } else if (fieldType.getTypeName().equals("java.lang.Boolean")) {
            return Boolean.valueOf(str);
        }
        return null;
    }


    public String[] getItemValues(final String name) {
        if (this.items.containsKey(name)) {
            return String[].class.cast(this.items.get(name));
        }
        return null;
    }

    public Map<String, Object> getItems() {
        return this.items;
    }

    public void validate(final ActionMapping mapping, final HttpServletRequest request) throws RuntimeException {
    }

    @Override
    public String toString() {
        String key;
        final StringBuilder form = new StringBuilder();
        final Iterator<String> keys = this.items.keySet().iterator();
        while (keys.hasNext()) {
            key = keys.next().toString();
            form.append("Key: ");
            form.append(key);
            form.append(" - Value: ");
            form.append(this.items.get(key));
            form.append("\n");
        }
        return form.toString();
    }

}
