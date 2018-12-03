package beans;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {

    static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.SSS").create();

    public static String toJsonString(final Object object) {
        return gson.toJson(object);
    }

    public static <T> T toObject(final String json, final Class<T> clazz) {
        final T jsonObj = gson.fromJson(json, clazz);
        return jsonObj;
    }

    public static <T> List<T> toObjectArray(final String json, final Class<T> clazz) {
        final T[] jsonArray = gson.fromJson(json, (Type) clazz);
        return Arrays.asList(jsonArray);
    }
}
