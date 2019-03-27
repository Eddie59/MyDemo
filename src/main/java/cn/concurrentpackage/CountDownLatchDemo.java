package cn.concurrentpackage;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
    /**
     * CountDownLatch， 可以用来在一个线程中等待多个线程完成任务的类；
     * 通常的使用场景是，某个主线程接到一个任务，起了n个子线程去完成，但是主线程需要等待这n个子线程都完成任务了以后才开始执行某个操作
     * @param args
     */
    public static void main(String[] args) {
        int count = 10;
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
                    countDownLatch.countDown();
                }
            }).start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("all threads have finished.");
    }

}
