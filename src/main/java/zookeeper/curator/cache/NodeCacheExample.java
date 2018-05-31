package zookeeper.curator.cache;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.data.Stat;


/**
 * NodeCacheExample class
 *
 * @author Administrator
 * @date
 */
public class NodeCacheExample {

    private static final String PATH = "/example/nodeCache";
    private static final String CONN = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {

        try {
            CuratorFramework client = CuratorFrameworkFactory.newClient(CONN, new ExponentialBackoffRetry(1000, 3));
            client.start();

            Stat stat = client.checkExists().forPath(PATH);
            if (stat == null) {
                client.create().creatingParentsIfNeeded().forPath(PATH);
            }


            final NodeCache cache = new NodeCache(client, PATH);
            cache.getListenable().addListener(() -> {
                ChildData childData = cache.getCurrentData();
                if (childData != null) {
                    System.out.println(childData.getPath() + childData.getData() + childData.getStat());
                }
            });
            cache.start();

            client.setData().forPath(PATH, "01".getBytes());
            Thread.sleep(100);
            client.setData().forPath(PATH, "02".getBytes());
            Thread.sleep(100);
            client.delete().deletingChildrenIfNeeded().forPath(PATH);
            Thread.sleep(1000 * 2);

            CloseableUtils.closeQuietly(cache);
            CloseableUtils.closeQuietly(client);

        } catch (Exception e) {
            System.out.println(e);
        } finally {


        }
    }




}
