package cn.reentrantlock;

import org.junit.Test;

/**
 * 使用synchronized来现实等待、通知
 *
 * @author Administrator
 * @date
 */
public class BoundedBufferSyn {
    final int[] items = new int[2];

    int putptr, takeptr, count;

    synchronized public void put(int x) {
        // 如果是full，则让这个企图put的线程等待
        while (count == items.length) {
            System.out.printf("----FULL---- The buffer is full!  %s has to wait.\n", Thread.currentThread().getName());
            // 这里的wait和Condition的await在功能上没有什么区别，重点在唤醒
            //当前线程wait，并释放synchronized锁，等待被唤醒
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }

        // 每次只要put成功，则通知一下 notEmpty，如果存在等待take的线程，则唤醒一个让它取
        items[putptr] = x;
        if (++putptr == items.length) {
            putptr = 0;
        }
        ++count;
        System.out.println(Thread.currentThread().getName() + " has put");
        // 唤醒所有等待线程，包括存的和取的，让它们再去抢一次锁，而无法只通知take特性的线程
        notifyAll();
    }

    synchronized public int take() {
        while (count == 0) {
            System.out.printf("----EMPTY---- The buffer is empty!  %s has to wait.\n",
                    Thread.currentThread().getName());
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        // 每次take成功，则通知 notFull，如果有等待put的线程，则让它放
        int x = items[takeptr];
        if (++takeptr == items.length) {
            takeptr = 0;
        }
        --count;
        System.out.println(Thread.currentThread().getName() + " has take");
        //唤醒所有等待线程，包括存的和取的,让它们再去抢一次锁，而无法只通知put特性的线程
        notifyAll();
        return x;
    }

    @Test
    public void run() {
        BoundedBufferSyn obj = new BoundedBufferSyn();
        try {
            Thread put1 = new Thread(() -> {
                obj.put(1);
            });
            put1.setName("put1");
            Thread put2 = new Thread(() -> {
                obj.put(2);
            });
            put2.setName("put2");
            Thread put3 = new Thread(() -> {
                obj.put(3);
            });
            put3.setName("put3");

            put1.start();
            put2.start();
            put3.start();

            Thread.sleep(10000);

            Thread take1 = new Thread(() -> {
                obj.take();
            });
            take1.setName("take1");
            take1.start();

            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception exp) {

        }

    }
}
