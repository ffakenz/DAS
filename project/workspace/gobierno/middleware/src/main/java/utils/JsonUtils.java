package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

    static final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss.SSS").create();

    public static String toJsonString(final Object object) {
        return gson.toJson(object);
    }

    public static <T> T toObject(final String json, final Class<T> clazz) {
        final T jsonObj = gson.fromJson(json, clazz);
        return jsonObj;
    }
}