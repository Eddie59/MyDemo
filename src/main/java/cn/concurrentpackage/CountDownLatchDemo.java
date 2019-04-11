package cn.concurrentpackage;

import org.junit.Test;

import java.util.concurrent.*;

public class CountDownLatchDemo {
    /**
     * CountDownLatch， 可以用来在一个线程中等待多个线程完成任务的类；
     * 通常的使用场景是，某个主线程接到一个任务，起了n个子线程去完成，但是主线程需要等待这n个子线程都完成任务了以后才开始执行某个操作
     * @param args
     */
    public static void main(String[] args) {
        int count = 10;
        //AQS设置state为10
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.currentThread().sleep(5*1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread " + index + " has finished...");
                    //每个线程执行结束，state减1
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            //state状态为0说明所有线程执行完毕
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all threads have finished.");
    }

    @Test
    public void demo() throws Exception{
        CountDownLatch countDownLatch=new CountDownLatch(4);
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 2, 5, TimeUnit.SECONDS, new LinkedBlockingQueue<>());
        for(int i=0;i<5;i++){
           Runnable task=new Runnable() {
               @Override
               public void run() {
                   try
                   {
                       Thread.sleep(1000);
                   }
                   catch (Exception e){
                   }
                   System.out.println(Thread.currentThread().getId()+ " finished");
                   countDownLatch.countDown();
               }
           };
            threadPoolExecutor.submit(task);
        }
        System.out.println("提交完毕");
        countDownLatch.await();
        System.out.println("over");
    }

}
