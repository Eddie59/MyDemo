package cn.concurrentpackage;

import org.junit.Test;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ExcutorTest {
    private static final Executor executor= Executors.newFixedThreadPool(2);

    /**
     * 把任务提交给线程池
     * @throws Exception
     */
    @Test
    public void executeTask()throws Exception{
        System.out.println("主线程的Id:"+Thread.currentThread().getId());
        for (int i=0;i<10;i++){
            //Executor 是基于生产者消费者模式的，提交任务的操作相当于生产者，执行任务的线程相当于消费。
            Runnable task=new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getId()+" "+ new Random().nextInt(100));
                }
            };
            //提交任务到线程池
            executor.execute(task);
        }
        Thread.sleep(10000);
    }

    /**
     * 串行执行任务
     * @throws Exception
     */
    @Test
    public void serialTask()throws Exception{
        System.out.println("主线程的Id:"+Thread.currentThread().getId());
        SerialExecutor serialExecutor=new SerialExecutor();
        for (int i=0;i<5;i++){
            serialExecutor.execute(()->{
                System.out.println(Thread.currentThread().getId()+" "+ new Random().nextInt(100));
            });
        }
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void newThreadTask() throws Exception{
        System.out.println("主线程的Id:"+Thread.currentThread().getId());
        ThreadExecutor threadExecutor=new ThreadExecutor();
        for (int i=0;i<5;i++){
            threadExecutor.execute(()->{
                System.out.println(Thread.currentThread().getId()+" "+ new Random().nextInt(100));
            });
        }
        Thread.sleep(10000);
    }
}


/**
 * 提交task给主线程去执行，也就是串行执行
 */
class SerialExecutor implements Executor{
    @Override
    public void execute(Runnable command) {
        command.run();
    }
}

/**
 * 每次提交任务，都创建一个线程来执行 command
 */
class ThreadExecutor implements Executor{
    @Override
    public void execute(Runnable command) {
        new Thread(command).start();
    }
}


