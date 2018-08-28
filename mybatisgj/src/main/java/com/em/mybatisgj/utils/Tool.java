package com.em.mybatisgj.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Tool class
 *
 * @author Administrator
 * @date
 */
public class Tool {
    private static Logger logger = LoggerFactory.getLogger(Tool.class);

    public static int nowInt() {
        return dateToInt(new Date(), 0);
    }

    public static int dateStrToInt(String dataStr) {
        if (StringUtils.isAllEmpty(dataStr)) {
            return 0;
        }
        if (dataStr.equals("null")) {
            return 0;
        }
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(dataStr);
            return dateToInt(date, 0);
        } catch (ParseException e) {
            logger.error("Date类型字符串转化为int时出错", e);
            return 0;
        }
    }

    /**
     * Date类型转化为int类型
     */
    public static int dateToInt(Date date, int def) {
        if (date == null) {
            return def;
        }

        int result = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = format.format(date);
            String[] array = dateStr.split("-");
            int year = Integer.parseInt(array[0]);
            int month = Integer.parseInt(array[1]);
            int day = Integer.parseInt(array[2]);
            result = year * 10000 + month * 100 + day;
        } catch (Exception exp) {
            logger.error("Date类型转化为int时出错", exp);
        }
        return result;
    }

    /**
     * Date类型转化为int类型
     */
    public static int dateToInth(Date date, int def) {
        if (date == null) {
            return def;
        }

        int result = 0;
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH");
            String dateStr = format.format(date);
            String[] array = dateStr.split("-");
            int year = Integer.parseInt(array[0]);
            int month = Integer.parseInt(array[1]);
            int day = Integer.parseInt(array[2]);
            int hour = Integer.parseInt(array[3]);
            result = year * 1000000 + month * 10000 + day * 100 + hour;
        } catch (Exception exp) {
            logger.error("Date类型转化为int时出错", exp);
        }
        return result;
    }


    public static String calDate(Date date, int calType, int val) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(calType, val);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
        return sf.format(calendar.getTime());
    }


    /**
     * @param fullcode
     * @return 把fullcode转化为股票代码
     */
    public static String convertFullcode(String fullcode) {
        if (StringUtils.isBlank(fullcode)) {
            return "";
        }

        Long Fullcode = 0L;
        try {
            Fullcode = Long.parseLong(fullcode);
        } catch (NumberFormatException e) {
            logger.error("A股 fullcode转化为long时异常", e);
            return "";
        }

        char stock[] = new char[10];
        stock[0] = '\0';
        int _pos = 30;
        long nType = (Fullcode & 0x80000000) >> 31;
        int j = 0;
        int nMarket = (int) (0x3f & Fullcode);

        if (nType == 0 && nMarket != 47) {
            for (int i = 0; i < 6; i++) {
                _pos -= 4;

                int d = (int) (0xf & (Fullcode >> _pos));
                if (d == 0xf) {
                    stock[j++] = '\0';
                    return null;
                } else {
                    stock[j++] = (char) (d + '0');
                }
            }

        } else {
            return null;//其他版块不作处理
        }
        stock[j++] = '\0';
        return String.valueOf(stock).trim();

    }

    /**
     * @param fullcode 行情的fullcode
     * @return 市场marketid
     */
    public static String fullcodeToMarketID(String fullcode) {
        if (StringUtils.isBlank(fullcode)) {
            return "";
        }

        if (fullcode.contains("HK")) {
            return "116";
        }

        Long code = 0L;
        try {
            code = Long.parseLong(fullcode);
        } catch (NumberFormatException e) {
            logger.error("fullcode转化为long时异常", e);
            return "";
        }

        int nMarket = (int) (0x3f & code);
        return String.valueOf(nMarket);
    }

    /**
     * @param str
     * @param cnt
     * @return Str字符串去除后cnt位
     */
    public static String delLastChar(String str, int cnt) {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        int strLen = str.length();
        if (strLen < cnt) {
            return "";
        }
        return str.substring(0, strLen - cnt);
    }
}
