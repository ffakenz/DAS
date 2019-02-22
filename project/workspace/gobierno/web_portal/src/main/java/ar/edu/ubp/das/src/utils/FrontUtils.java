package ar.edu.ubp.das.src.utils;

public class FrontUtils {
    public static String emptyIfNull(final Object value) {
        return value == null ? "" : value.toString();
    }
}
