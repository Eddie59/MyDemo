package cn;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 * Del1 class
 *
 * @author Administrator
 * @date
 */
public class Del1 {
    @Test
    public void test() throws Exception{

        new Thread(()->{
            System.out.println("1");
        }).start();

        Thread thread=new Thread(new MyRun());
        thread.start();
        thread.join();


    }
}


class MyRun implements Runnable {
    @Override
    public void run() {
        System.out.println("a");
    }
}
