package zookeeper.origin.version;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.concurrent.CountDownLatch;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo implements Watcher {
    // 屏障，计数器
    private static CountDownLatch downLatch = new CountDownLatch(1);

    private static ZooKeeper zookeeper = null;

    public static void main(String[] args) throws Exception {

        zookeeper = new ZooKeeper("127.0.0.1:2181", 60000, new Demo());

        System.out.println("zookeeper.getState()1 : " + zookeeper.getState());

        try {
            downLatch.await();// 在计数器未归零之前，所有线程等待
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("zookeeper.getState()2 : " + zookeeper.getState());

        zookeeper.create("/cyx", "ccc".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        zookeeper.getData("/cyx", true, null);

        // 第一次设置，版本号加1
        Stat statFirst = zookeeper.setData("/cyx", "456".getBytes(), -1);
        System.out.println(statFirst.getCzxid() + " , " + statFirst.getMzxid() + " , " + statFirst.getVersion());

        // 第二次设置，版本号加1
        Stat stat2 = zookeeper.setData("/cyx", "789".getBytes(), -1);
        System.out.println(stat2.getCzxid() + " , " + stat2.getMzxid() + " , " + stat2.getVersion());

        // 使用第一次设置的版本号，进行更新，发生异常
        try {
            zookeeper.setData("/cyx", "123".getBytes(), statFirst.getVersion());
        } catch (Exception e) {
            zookeeper.delete("/cyx",-1);
            zookeeper.close();
            e.printStackTrace();
        }

        /**
         * 解释下setData时的"-1"：
         在ZooKeeper中，数据版本都是从0开始计数额，所以严格的讲，"-1"不是一个合法得到数据版本，它仅仅是一个标示符。
         如果客户端传入的版本参数是"-1"，就是告诉zookeeper服务器，客户端需要基于数据的最新版本进行更新操作。
         */

    }

    @Override
    public void process(WatchedEvent event) {

        System.out.println("receive watched event : " + event);

        if (Event.KeeperState.SyncConnected == event.getState()) {

            if (Event.EventType.None == event.getType() && null == event.getPath()) {
                downLatch.countDown();// 计数器-1
            }
        }
    }
}
