package zookeeper.curator.lock.sharedreentrantlock;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * FakeLimitedResource class
 *
 * @author Administrator
 * @date
 */
public class FakeLimitedResource {
    private final AtomicBoolean inUse = new AtomicBoolean(false);

    public void use() throws InterruptedException {
        //这里的false指的是预期的值，就是预测程序从上面的赋值执行到这里，没人修改这个值，还是原来的false
        //拿这个值，和内存中的值（当前真实的值，有可能被其它线程修改过了），相比如，如果相等，说明没有被修改过，就把新值true写入内存，compareAndSet方法返回true，
        // 表示已经写入，如果不相等，说明已经被其它线程修改过了，放弃这次修改，compareAndSet方法返回false，表示没有写入

        if (!inUse.compareAndSet(false, true)) {
            throw new IllegalStateException("Needs to be used by one client at a time");
        }
        try {
            Thread.sleep((long) (2000));
        } finally {
            inUse.set(false);
        }
    }

}
