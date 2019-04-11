package cn.concurrentpackage.retval;

import org.junit.Test;

import java.util.concurrent.*;

public class CallableDemo {

    @Test
    public void demo1() throws Exception {
        //Callable只是一个@FunctionalInterface，没有特别之处，唯一的方法要返回T类型的值
        Callable<Integer> callable1 = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                Thread.sleep(5000);
                return 100;
            }
        };

        //FutureTask实现了Runnable, Future，所以，FutureTask可以作为Future来接收Callable的返回值
        //为什么要使用FutureTask来包装呢，因为FutureTask实现了Runnable，所以可以使用线程或线程池来执行
        FutureTask<Integer> future = new FutureTask<Integer>(callable1);
        //1,FutureTask实现了Runnable，所以可以使用线程执行
        new Thread(future).start();
 /*       //2,FutureTask实现了Runnable，所以可以线程池执行
        ExecutorService executorService1 = Executors.newFixedThreadPool(2);
        executorService1.submit(future);*/

        /*可以直接提交callable对象到线程池，submit方法内部会自动创建FutureTask对象
        public <T> Future<T> submit(Callable<T> task) {
            RunnableFuture<T> ftask = newTaskFor(task);
            execute(ftask);
            return ftask;
        }*/
        ExecutorService executorService2 = Executors.newFixedThreadPool(2);
        executorService2.submit(callable1);


        //因为FutureTask实现了Future，所以可以通过Future来获取线程执行的结果
        Integer result = future.get();
        System.out.println(result);
    }

    /**
     * 怎样判断线程池里的任务都执行完毕了
     *
     * @throws Exception
     */
    @Test
    public void run2() throws Exception {
        Callable<String> callable1 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(4000);
                return "a";

            }
        };

        Callable<String> callable2 = new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(6000);
                return "b";
            }
        };

        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10000));
        poolExecutor.submit(callable1);
        poolExecutor.submit(callable2);

        while (true) {
            if (poolExecutor.getCompletedTaskCount() == poolExecutor.getTaskCount()) {
                System.out.println("OK");
                break;
            }
            Thread.sleep(1000);
        }
    }


}
