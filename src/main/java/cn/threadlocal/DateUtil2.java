package cn.threadlocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * DateUtil2 class
 * 注意这里的方法都是static类型的，都是相对于类的，而不是类对象的
 * @author Administrator
 * @date
 */
public class DateUtil2 {

    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    public final static String MDHMSS = "MMddHHmmssSSS";
    public final static String YMDHMS = "yyyyMMddHHmmss";
    public final static String YMDHMS_ = "yyyy-MM-dd HH:mm:ss";
    public final static String YMD = "yyyyMMdd";
    public final static String YMD_ = "yyyy-MM-dd";
    public final static String HMS = "HHmmss";

    /**
     * 根据map中的key得到对应线程的sdf实例
     *
     * @param pattern map中的key
     * @return 该实例
     */
    private static SimpleDateFormat getSdf(final String pattern) {

        ThreadLocal<SimpleDateFormat> sdfThread = sdfMap.get(pattern);
        if (sdfThread == null) {
            //双重检验,防止sdfMap被多次put进去值
            //这里synchronized (DateUtil2.class)，是因为当前方法SimpleDateFormat方法是static的，所以监视器要设置成“DateUtil2.class类对象”
            synchronized (DateUtil2.class) {
                sdfThread = sdfMap.get(pattern);
                if (sdfThread == null) {
                    System.out.println("put new sdf of pattern " + pattern + " to map");
                    sdfThread = new ThreadLocal<SimpleDateFormat>() {
                        @Override
                        protected SimpleDateFormat initialValue() {
                            System.out.println("threadid: " + Thread.currentThread().getId() + " init pattern: " + pattern);

                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, sdfThread);
                }
            }
        }
        //获取存放在当前线程的ThreadLocal.ThreadLocalMap属性里的对象，这里是new出来的SimpleDateFormat对象
        return sdfThread.get();
    }

    /**
     * 按照指定pattern解析日期
     *
     * @param date    要解析的date
     * @param pattern 指定格式
     * @return 解析后date实例
     */
    public static Date parseDate(String date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        try {
            return getSdf(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("解析的格式不支持:" + pattern);
        }
        return null;
    }

    /**
     * 按照指定pattern格式化日期
     *
     * @param date    要格式化的date
     * @param pattern 指定格式
     * @return 解析后格式
     */
    public static String formatDate(Date date, String pattern) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        } else {
            return getSdf(pattern).format(date);
        }
    }

    public static void main(String[] args) {



        new Thread(()->{
            DateUtil2.formatDate(new Date(),MDHMSS);
        }).start();

        new Thread(()->{
            DateUtil2.formatDate(new Date(),MDHMSS);
        }).start();

        new Thread(()->{
            DateUtil2.formatDate(new Date(),MDHMSS);
        }).start();


    }
}
