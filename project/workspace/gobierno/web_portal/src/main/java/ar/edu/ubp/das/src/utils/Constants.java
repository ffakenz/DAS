package ar.edu.ubp.das.src.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Calendar;

public final class Constants {

    //************ CONSUMERS ROLES ****************************
    public static final String ROL_GOBIERNO = "gobierno";
    public static final String ROL_CONSUMER = "consumer";

    //************ PARAMS SERVLETS ****************************
    public static final String USER_TYPE = "userType";
    public static final String SSID = "ssid";
}


class Runner {
    public static void main(String[] args) {
        Timestamp ts = Timestamp.from(Instant.now());
        Calendar cal = Calendar.getInstance();
        cal.setTime(ts);
        cal.add(Calendar.DAY_OF_WEEK, 14);
        ts.setTime(cal.getTime().getTime()); // or
        ts = new Timestamp(cal.getTime().getTime());
        System.out.println(ts);
    }
}