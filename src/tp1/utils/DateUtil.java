/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp1.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 *
 * @author seb
 */
public class DateUtil {

    private static DateFormat getISO8601Format() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");// ISO8601 =>
        // "yyyy-MM-dd'T'HH:mm:ss'Z'";
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }

    public static Date parse(String date) {
        try {
            return getISO8601Format().parse(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String format(Date date) {
        return getISO8601Format().format(date);
    }
}
