package main.java.em.threadlocal;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * DateUtil2_1 class
 *
 * @author Administrator
 * @date
 */
public class DateUtil2_1 {
    private static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    private static List<Thread> threads = new ArrayList<>();

    public static void main(String... args) {
        for (int i = 0; i < 3; i++) {
            threads.add(new Thread(new Runnable() {
                @Override
                public void run() {
                   SimpleDateFormat simpleDateFormat= local.get();
                   String result= simpleDateFormat.format(new Date());
                    System.out.println(result);
                }
            }));
        }

        for (int i=0;i<3;i++)
        {
            threads.get(i).start();
        }
    }
}
