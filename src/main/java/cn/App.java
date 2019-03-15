package cn;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        Thread thread=new Thread(()->{
            while (true){
                try {
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName()+" "+Thread.currentThread().isDaemon());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


        System.out.println(thread.currentThread().getName()+" "+thread.currentThread().isDaemon());

    }
}
