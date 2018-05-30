package zookeeper.curator.cache;

import java.util.List;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.utils.CloseableUtils;
import org.apache.zookeeper.data.Stat;

/**
 * PathCacheDemo class
 *
 * @author Administrator
 * @date
 */
public class PathCacheDemo {
    private static final String PATH = "/example";
    private static final String CONN = "127.0.0.1:2181";

    public static void main(String[] args) throws Exception {

        try {
            CuratorFramework client = CuratorFrameworkFactory.newClient(CONN, 5000, 5000, new RetryNTimes(3, 5000));
            client.start();

            Stat stat = client.checkExists().forPath(PATH);
            if (stat == null) {
                client.create().creatingParentsIfNeeded().forPath(PATH);
            }


            //PathChildrenCache 对监视路径PATH的子节点 增加 修改 删除时 触发通知
            //false表示不缓存结点数据，true缓存数据
            PathChildrenCache cache = new PathChildrenCache(client, PATH, false);
            PathChildrenCacheListener listener = new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent event) throws Exception {
                    System.out.println("事件类型：" + event.getType());
                    //event.getData()表示，经过事件以后，当前结点缓存在cache中的数据
                    //如果PathChildrenCache设置不缓存数据，则event.getData()为null
                    ChildData curData= event.getData();
                    if (null != curData) {
                        System.out.println("节点数据：" + curData.getPath() + " = " + new String(curData.getData()));
                    }
                }
            };
            cache.getListenable().addListener(listener);
            cache.start();


            client.create().creatingParentsIfNeeded().forPath("/example/nodeCache");
            client.setData().forPath("/example/nodeCache","abc".getBytes());
            client.setData().forPath("/example/nodeCache","def".getBytes());
            client.delete().deletingChildrenIfNeeded().forPath("/example/nodeCache");

            //所有缓存里面的数据
            List<ChildData> datas= cache.getCurrentData();
            for (ChildData data:datas)
            {
                System.out.println("getCurrentData:" + data.getPath() + " = " + new String(data.getData()));
            }

            CloseableUtils.closeQuietly(cache);
            CloseableUtils.closeQuietly(client);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
