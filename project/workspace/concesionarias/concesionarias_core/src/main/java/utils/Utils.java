package utils;

import java.sql.Timestamp;
import java.time.Instant;

public final class Utils {
    public static Timestamp fromStringToTimestamp(final String strTime) {
        final Long longNanos = Long.valueOf(strTime);
        final Instant instant = Instant.ofEpochMilli(longNanos);
        return Timestamp.from(instant);
    }
}
