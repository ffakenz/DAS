package ar.edu.ubp.das.src.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;

public class DateUtils {

    /**
     * @param date
     * @param days
     * @return
     */
    public static Timestamp getTimestampFrom(final Timestamp date, final Integer days) {

        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_WEEK, days);
        return new Timestamp(cal.getTime().getTime());
    }

    public static Date getDateFromDays(final long days) {

        final LocalDate localDate = LocalDate.now().plusDays(days);
        final java.util.Date uDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new Date(uDate.getTime());
    }

    public static Date getDayDate() {

        final LocalDate localDate = LocalDate.now();
        final java.util.Date uDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new Date(uDate.getTime());
    }

    /**
     * this method get timestamp object plus any number of days
     *
     * @param days
     * @return
     */
    public static Timestamp getTimestamp(final long days) {
        return Timestamp.valueOf(ZonedDateTime.now().plusDays(days).toLocalDateTime());
    }
}
