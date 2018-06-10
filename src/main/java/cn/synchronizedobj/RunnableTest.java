package main.java.em.synchronizedobj;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * RunnableTest class
 *
 * @author Administrator
 * @date
 */
public class RunnableTest implements Runnable {
    private static boolean flag = true;

    /**
     *  private static synchronized void testSyncMethod()这里使用了static，那么synchronized同步的对象就是RunnableTest类
     * 相当于synchronized(RunnableTest.Class)
     * 如果把这里的static去掉,private synchronized void testSyncMethod() {
     * 这样，监视器就是RunnableTest类的一个对象的testSyncMethod方法，也就是说，同一个RunnableTest类的对象，不能
     */
    private static synchronized void testSyncMethod() {
        for (int i = 0; i < 100; i++) {
            System.out.println("testSyncMethod:" + i);
        }
    }

    private synchronized void testSyncMethod2() {
        for (int i = 0; i < 100; i++) {
            System.out.println("testSyncMethod2:" + i);
        }
    }

    /**
     * synchronized (this)这里同步的是调用此方法的对象
     * synchronized (RunnableTest.class)监视器是RunnableTest类，跟上面一样，所以同一时间只能执行一个方法
     */
    private void testSyncBlock() {
        synchronized (this) {
//        synchronized (RunnableTest.class) {
            for (int i = 0; i < 100; i++) {
                System.out.println("testSyncBlock:" + i);
            }
        }
    }

    @Override
    public void run() {
        if (flag) {
            flag = false;
            testSyncMethod();
        } else {
            flag = true;
            testSyncBlock();
        }
    }

    public static void main(String... args) {
//        ExecutorService exec = Executors.newFixedThreadPool(2);
//        exec.execute(new RunnableTest());
//        exec.execute(new RunnableTest());
//        exec.shutdown();


//        // private synchronized void testSyncMethod2(),监视器是同一个对象的testSyncMethod2方法，就是说，一个对象，一次只能有一个纯种访问testSyncMethod2方法
//        RunnableTest obj =new RunnableTest();
//        new Thread(()->obj.testSyncMethod2()).start();
//        new Thread(()->obj.testSyncMethod2()).start();
        //如果是多个对象，是不受影响的，可以同时访问的
        RunnableTest obj1 =new RunnableTest();
        RunnableTest obj2 =new RunnableTest();
        new Thread(()->obj1.testSyncMethod2()).start();
        new Thread(()->obj2.testSyncMethod2()).start();



    }
}
