package cn.concurrentpackage;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class TestCyclicBarrier {
    public static void main(String[] args) {
        //1.得到一个CyclicBarrier实例
        CyclicBarrier cb = new CyclicBarrier(4);
        new Thread(new Fishing(cb),"Thread1Name").start();
        new Thread(new Fishing(cb),"Thread2Name").start();
        new Thread(new Fishing(cb),"Thread3Name").start();
        new Thread(new Fishing(cb),"Thread4Name").start();
    }

    static class Fishing implements Runnable{
        CyclicBarrier cb;
        public Fishing(CyclicBarrier cb) {
            this.cb = cb;
        }

        @Override
        public void run() {
            try {
                cb.await();
                System.out.println("第（" + Thread.currentThread().getName() + "）个人开始钓鱼");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
