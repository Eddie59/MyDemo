package zookeeper.curator.lock.sharedreentrantlock;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * InterProcessMutexDemo class
 *
 * @author Administrator
 * @date
 */
public class InterProcessMutexDemo {

    /**
     * InterProcessMutex,意味着，同一个客户端在拥有锁的同时，可以多次获取，不会被阻塞，lock.acquire()多次返回true
     */
    private InterProcessMutex lock;
    private final FakeLimitedResource resource;
    private final String clientName;

    public InterProcessMutexDemo(CuratorFramework client, String lockPath, FakeLimitedResource resource, String clientName) {
        this.resource = resource;
        this.clientName = clientName;
        this.lock = new InterProcessMutex(client, lockPath);
    }

    public void doWork(long time, TimeUnit unit,int j) throws Exception {
        //lock.acquire(),true获得锁,false没有获得锁
        if (!lock.acquire(time, unit)) {
            throw new IllegalStateException(clientName + " could not acquire the lock");
        }
        try {
            System.out.println(clientName + " get the lock"+"第"+j+"次请求锁");
            //模拟访问资源
            resource.use();
        } finally {

            //使用release表示用InterProcessMutex实现了排它锁
            //注释release，表示同一客户端可以多次获得锁
//            System.out.println(clientName + " releasing the lock"+"第"+j+"次释放锁");
//            lock.release();      //资源使用完毕，释放锁
        }
    }

    private static final int QTY =5;//线程数
    private static final int REPETITIONS =  5;//每个线程请求锁的次数
    private static final String PATH = "/examples/locks";

    public static void main(String[] args) throws Exception {
        final FakeLimitedResource resource = new FakeLimitedResource();
        ExecutorService service = Executors.newFixedThreadPool(QTY);

        try {
            for (int i = 0; i < QTY; ++i) {
                final int index = i;
                Callable<Void> task = new Callable<Void>() {
                    @Override
                    public Void call() throws Exception {
                        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new ExponentialBackoffRetry(1000, 3));
                        try {
                            client.start();
                            final InterProcessMutexDemo example = new InterProcessMutexDemo(client, PATH, resource, "Client " + index);
                            for (int j = 0; j < REPETITIONS; ++j) {
                                example.doWork(10, TimeUnit.SECONDS,j);
                            }
                        } catch (Throwable e) {
                            e.printStackTrace();
                        } finally {
                            CloseableUtils.closeQuietly(client);
                        }
                        return null;
                    }
                };
                service.submit(task);

            }
            service.shutdown();
            service.awaitTermination(10, TimeUnit.MINUTES);
        } finally {

        }
    }
}
