package cn.concurrentpackage.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Executors {

    public static void main(String... args) throws Exception {

        ExecutorService fixedThreadPool = java.util.concurrent.Executors.newFixedThreadPool(3);
        fixedThreadPool.submit(() -> System.out.println(Thread.currentThread().getId()));


        ScheduledExecutorService scheduledExecutorService = java.util.concurrent.Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(() -> System.out.println("abc"), 5, TimeUnit.SECONDS);


        ExecutorService threadPool = java.util.concurrent.Executors.newCachedThreadPool();//线程池里面的线程数会动态变化，并可在线程线被移除前重用
        for (int i = 1; i <= 3; i++) {
            final int task = i;   //10个任务
            TimeUnit.SECONDS.sleep(1);
            threadPool.execute(new Runnable() {    //接受一个Runnable实例
                public void run() {
                    System.out.println("线程名字： " + Thread.currentThread().getName() + "  任务名为： " + task);
                }
            });
        }



    }

}
