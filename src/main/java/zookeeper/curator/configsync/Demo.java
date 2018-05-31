package zookeeper.curator.configsync;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    private static final String PATH = "/songweb/dbconfig";
    private static final String CONN = "127.0.0.1:2181";

    public static void main(String... args) {
        try {
            CuratorFramework client = CuratorFrameworkFactory.builder()
                    .connectString(CONN)
                    .retryPolicy(new RetryNTimes(3, 5000))
                    .sessionTimeoutMs(5000)
                    .connectionTimeoutMs(5000)
                    .build();
            client.start();

            Stat stat = client.checkExists().forPath(PATH);
            if (stat == null) {
                client.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(PATH, "abc".getBytes());
            }

//dataIsCompressed为true，不会通知
//            NodeCache cache = new NodeCache(client, PATH,true);
            NodeCache cache = new NodeCache(client, PATH);
            cache.getListenable().addListener(() -> {
                ChildData childData = cache.getCurrentData();
                if (childData != null) {
                    System.out.println("最新的配置文件：" + childData.getData());
                }
            });
            cache.start();

            Thread.sleep(Long.MAX_VALUE);
            cache.close();
            client.close();

        } catch (Exception exp) {
            System.out.println(exp);
        }


    }
}
