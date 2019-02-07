package ar.edu.ubp.das.src.utils;

import java.util.Calendar;
import java.util.HashMap;

public class Month {
    private int month;
    private int year;
    private int days[][];
    private int numberOfWeeks;
    private static HashMap months = new HashMap();

    private Month() {
    }

    private Month(final int month, final int year) {
        days = new int[6][7];
        numberOfWeeks = 0;
        this.month = month;
        this.year = year;
        buildWeeks();
    }

    public int getMonth() {
        return month;
    }

    public static Month getMonth(final int month, final int year) {
        final String key = String.valueOf((new StringBuffer(String.valueOf(month))).append("/").append(year));
        if (months.containsKey(key)) {
            return (Month) months.get(key);
        } else {
            final Month newMonth = new Month(month, year);
            months.put(key, newMonth);
            return newMonth;
        }
    }

    private void buildWeeks() {
        final Calendar c = Calendar.getInstance();
        c.setFirstDayOfWeek(1);
        c.set(year, month, 1);
        for (; c.get(2) == month; c.add(5, 1)) {
            final int weekNumber = c.get(4) - 1;
            final int dayOfWeek = calculateDay(c.get(7));
            days[weekNumber][dayOfWeek] = c.get(5);
            numberOfWeeks = weekNumber;
        }
    }

    public int calculateDay(final int day) {
        if (day == 1)
            return 0;
        if (day == 2)
            return 1;
        if (day == 3)
            return 2;
        if (day == 4)
            return 3;
        if (day == 5)
            return 4;
        if (day == 6)
            return 5;
        return day != 7 ? 7 : 6;
    }

    public int[][] getDays() {
        return days;
    }

    public int getNumberOfWeeks() {
        return numberOfWeeks + 1;
    }

    public int getYear() {
        return year;
    }

}

