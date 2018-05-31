package em.reentrantlock;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用ReentrantLock来实现串行化
 *
 * @author Administrator
 * @date
 */
public class Sync {

    private Lock lock=new ReentrantLock();

    public void test()
    {
        //和synchronized一样，都可以同步执行，lock方法获得锁，unlock方法释放锁
        lock.lock();
        for (int i=0;i<5;i++)
        {
            System.out.println("ThreadName=" + Thread.currentThread().getName()
                    + (" " + (i + 1)));
        }
        lock.unlock();
    }

    @Test
    public void run()
    {
        try {
            CountDownLatch latch=new CountDownLatch(3);
            new Thread(()->{
                test();
                latch.countDown();
            }).start();
            new Thread(()->{
                test();
                latch.countDown();
            }).start();
            new Thread(()->{
                test();
                latch.countDown();
            }).start();
            latch.await();
        }
        catch (Exception exp)
        {
            System.out.println(exp);
        }



    }

}
