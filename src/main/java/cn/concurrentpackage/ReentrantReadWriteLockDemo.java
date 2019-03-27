package cn.concurrentpackage;

import org.junit.Test;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockDemo {
    /**
     * 读锁可以有很多个锁同时上锁，只要当前没有写锁；
     * 写锁是排他的，上了写锁，其他线程既不能上读锁，也不能上写锁；同样，需要上写锁的前提是既没有读锁，也没有写锁；
     * 两个写锁不能同时获得无需说明，下面一段程序说明下上了读锁以后，其他线程需要上写锁也无法获得
     */
    @Test
    public void testRWLock_getw_onr() {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        final Lock rlock = lock.readLock();
        final Lock wlock = lock.writeLock();

        final CountDownLatch l = new CountDownLatch(2);

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + "now to get rlock");
                rlock.lock();

                try {
                    Thread.currentThread().sleep(5 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                System.out.println(System.currentTimeMillis() + "now to unlock rlock");
                rlock.unlock();

                l.countDown();
            }
        }).start();

        // start w thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(System.currentTimeMillis() + "now to get wlock");
                wlock.lock();
                System.out.println(System.currentTimeMillis() + "now to unlock wlock");
                wlock.unlock();

                l.countDown();
            }
        }).start();

        try {
            l.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date() + "finished");
    }


    /**
     在一个线程中，读锁不能直接升级为写锁，但是写锁可以降级为读锁；
     这意思是，如果你已经有了读锁，再去试图获得写锁，将会无法获得， 一直堵住了；
     但是如果你有了写锁，再去试图获得读锁，没问题；
     */
    @Test
    public void testRWLock_downgrade(){
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        Lock rlock = lock.readLock();
        Lock wlock = lock.writeLock();

        System.out.println("now to get wlock");

        wlock.lock();
        System.out.println("now to get rlock");
        //有写锁，获取读锁时，可以获取
        rlock.lock();

        System.out.println("now to unlock wlock");

        wlock.unlock();

        System.out.println("now to unlock rlock");
        rlock.unlock();

        System.out.println("finished");
    }


    @Test
    public void testRWLock_upgrade()
    {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        Lock rlock = lock.readLock();
        Lock wlock = lock.writeLock();

        System.out.println("now to get rlock");
        rlock.lock();

        System.out.println("now to get wlock");
        //有了读锁，获取写锁时阻塞
        wlock.lock();

        System.out.println("now to unlock wlock");
        wlock.unlock();

        System.out.println("now to unlock rlock");
        rlock.unlock();

        System.out.println("finished");
    }





}
