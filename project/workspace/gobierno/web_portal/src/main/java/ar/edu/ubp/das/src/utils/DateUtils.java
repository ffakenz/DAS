package ar.edu.ubp.das.src.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
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

        LocalDate localDate = LocalDate.now().plusDays(days);
        java.util.Date uDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new Date(uDate.getTime());
    }

    public static Date getDayDate() {

        LocalDate localDate = LocalDate.now();
        java.util.Date uDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return new Date(uDate.getTime());
    }
}
