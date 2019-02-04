package ar.edu.ubp.das.src.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

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

    /**
     * @param timestamp
     * @return
     */
    public static Date getDayDateFromTimestamp(final Timestamp timestamp) {

        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(timestamp.getTime());
        try {
            return formatter.parse(date.toString());
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date getDayDate() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(Timestamp.from(Instant.now()).getTime());
        try {
            return formatter.parse(date.toString());
        } catch (ParseException e) {
            return null;
        }

    }
}
