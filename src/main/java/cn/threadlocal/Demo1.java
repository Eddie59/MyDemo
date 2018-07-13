package main.java.em.threadlocal;

import java.util.ArrayList;
import java.util.List;

/**
 * Demo1 class
 *
 * @author Administrator
 * @date
 */
public class Demo1 {
    private static ThreadLocal<Integer> local=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    private static List<Thread> threads=new ArrayList<>();

    public static void main(String...args)
    {
        for (int i=0;i<3;i++)
        {
            threads.add(new Thread(new Runnable(){
                @Override
                public void run() {
                    int num=local.get();
                    num+=10;
                    local.set(num);
                    System.out.println(Thread.currentThread().getName()+"  "+local.get());
                }
            }));
        }
        for (int i=0;i<3;i++)
        {
            threads.get(i).start();
        }
    }
}
