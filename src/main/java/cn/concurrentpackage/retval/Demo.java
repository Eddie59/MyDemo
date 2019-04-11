package cn.concurrentpackage.retval;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {

    public static void main(String... args) throws Exception {


        ThreadPoolExecutor threadPool = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);
        Future<String> future = threadPool.submit(new RetThread());
        System.out.println("main线程去做其它事");
        System.out.println(future.get());
        System.out.println("over");


        Callable<Integer> callable1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return 100;
            }
        };
        //FutureTask实现了Runnable, Future接口，需要Thread或者线程池去执行，
        //区别在于，FutureTask可以
        FutureTask<Integer> future1 = new FutureTask<Integer>(callable1);

        new Thread(future1).start();

        System.out.println("main干点别的");

        System.out.println(future1.get());


/*
        Integer result1 = Integer.valueOf(1);
        FutureTask<Integer> future2 = new FutureTask<Integer>(() -> {
            try {
                Thread.sleep(5000);
                System.out.println("abc");
            } catch (Exception e) {
                e.printStackTrace();
            }
            //return 100;
        }, result1);
        new Thread(future2).start();
        System.out.println(future2.get());
*/

    }
}












