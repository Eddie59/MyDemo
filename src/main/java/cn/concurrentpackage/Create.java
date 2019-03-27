package cn.concurrentpackage;

import org.junit.Test;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class Create {

    @Test
    public void createThread_Thread() throws Exception{
        //1,继承Thread类，实现线程
        Thread thread=new ExtendThread();
        thread.start();
        thread.join();
    }

    class ExtendThread extends Thread{
        @Override
        public void run() {
            System.out.println("extend Thread");
        }
    }

    //2,实现Runnable接口，实现线程
    @Test
    public void implRunnable(){
        new Thread(new ImplRunable()).start();
        //使用lambda来实现Runable接口
        new Thread(()->{
            System.out.println("impl Runnable lambda");
        }).start();
    }
    class ImplRunable implements Runnable{
        @Override
        public void run() {
            System.out.println("impl Runnable");
        }
    }


    @Test
    public void implCallable() throws Exception{
        FutureTask<Integer> task = new FutureTask<>(new ImplCallable());
        new Thread(task,"thread name").start();
        //线程阻塞，期望15秒内子线程有返回值
        Integer ret= task.get(15L, TimeUnit.SECONDS);
        System.out.println("子线程的返回值："+ret);
    }

    /**
     * Callable<Integer> 代表返回值为Integer类型
     */
    class ImplCallable implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            Thread.sleep(10000);
           return 10;
        }
    }

}
