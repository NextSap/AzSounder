package com.nextsap.sounder.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A class to manage date
 */
public class DateUtils {

    /**
     * Current time millis
     *
     * @return the current time
     */
    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * Get the current time with a format
     *
     * @param format is the format
     * @return the current time parsed with the format
     */
    public static String getFormat(String format) {
        return getFormat(format, getCurrentTimeMillis());
    }

    /**
     * Get a date with a format
     *
     * @param format is the format
     * @param time   is the time
     * @return the time parsed with the format
     */
    public static String getFormat(String format, long time) {
        SimpleDateFormat formater = new SimpleDateFormat(format);
        return formater.format(new Date(time));
    }

    /**
     * Get the time from a date
     *
     * @param date the date
     * @return the time
     */
    public static long getTime(String date) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String current_date = getFormat("dd/MM/yyyy ") + date;
        try {
            Date d = f.parse(current_date);
            return d.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * If the time is longer than x
     *
     * @param value the time
     * @param start first time
     * @return a boolean
     */
    public static boolean startAt(long value, long start) {
        return value >= start;
    }
}
