package ar.edu.ubp.das.src.utils;

import java.sql.Timestamp;
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
}
