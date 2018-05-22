package main.java.em.threadlocal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtil1 class
 *
 * @author Administrator
 * @date
 */
public class DateUtil1 {
    private final static SimpleDateFormat sdfyhm = new SimpleDateFormat("yyyyMMdd");

    public synchronized static Date parseymdhms(String source) {
        try {
            return sdfyhm.parse(source);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
}
