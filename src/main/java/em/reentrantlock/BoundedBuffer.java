package em.reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用ReentrantLock来现实等待、通知。一个Lock对象里面可以创建多个Condition实例，线程对象可以注册在指定的Condition实例中了，
 * 从而有选择地通知wait线程，也就相当于使用Condition实例，给wait线程分类，然后按类别通知wait线程
 * 使用synchronized来现实等待、通知，就相当于一个Condition实例，所有的wait线程只有一个组中，只能全部通知所有的wait线程，
 * 不能指定通知的类型
 *
 * @author Administrator
 * @date
 */
public class BoundedBuffer {
    final ReentrantLock lock = new ReentrantLock();
    // notFull 才能put
    final Condition notFull = lock.newCondition();
    // notEmpty 才能take
    final Condition notEmpty = lock.newCondition();

    final int[] items = new int[2];
    int putptr, takeptr, count;

    public void put(int x) throws InterruptedException {
        // 每次put之前线程得获得这个锁才行
        lock.lock();
        try {
            // 如果是full，则让这个企图put的线程等待
            while (count == items.length) {
                System.out.printf("----FULL---- The buffer is full!  %s has to wait.\n",
                        Thread.currentThread().getName());
                notFull.await();
            }

            // 每次只要put成功，则通知一下 notEmpty，如果存在等待take的线程，则唤醒一个让它取
            items[putptr] = x;
            if (++putptr == items.length) {
                putptr = 0;
            }
            ++count;
            //通知所有的taken线程来取数据
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public int take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.printf("----EMPTY---- The buffer is empty!  %s has to wait.\n",
                        Thread.currentThread().getName());
                notEmpty.await();
            }
            // 每次take成功，则通知 notFull，如果有等待put的线程，则让它放
            int x = items[takeptr];
            if (++takeptr == items.length) {
                takeptr = 0;
            }
            --count;
            //通知所有put线程来存放数据
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}
