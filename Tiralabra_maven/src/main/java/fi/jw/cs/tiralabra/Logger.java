package fi.jw.cs.tiralabra;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * @author Jan Wikholm <jw@jw.fi>
 * @since 2013-08-26
 */
public class Logger {

    static boolean LOGGING_ENABLED = true;
    static long SECOND = 1000;
    static long MINUTE = 60 * SECOND;
    static long HOUR = 60 * MINUTE;

    /**
     * Format current date in ISO 8601 format // from http://stackoverflow.com/a/3914498
     *
     * @return yyyy-mm-ddTHHmmZ
     */
    public static String getTimestamp() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        df.setTimeZone(tz);
        String nowAsISO = df.format(new Date());
        return nowAsISO;
    }

    public static void log(String string) {
        if (LOGGING_ENABLED) {
            System.out.print(getTimestamp() + " >> ");
            System.out.print(string);
            System.out.println("");
        }
    }

    public static void logf(String pattern, Object... objects) {
        if (LOGGING_ENABLED) {
            System.out.print(getTimestamp() + " >> ");
            System.out.printf(pattern, objects);
            System.out.println("");
        }
    }

    public static String prettyPrintDuration(long ms) {
        return prettyPrintDuration((double) ms);
    }

    public static String prettyPrintDuration(double ms) {
        if (ms > HOUR) {
            return "" + Math.round(ms / HOUR) + " h";
        } else if (ms > MINUTE) {
            return "" + Math.round(ms / MINUTE) + " min";
        } else if (ms > SECOND) {
            return "" + Math.round(ms / SECOND) + " s";
        } else {
            return "" + ms + " ms";
        }
    }
}
