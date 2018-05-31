package zookeeper.curator.configsync;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * CongfigSync class
 *
 * @author Administrator
 * @date
 */
public class CongfigSync {
    private static final String PATH = "/songweb/dbconfig";
    private static final String CONN = "127.0.0.1:2181";



    public CuratorFramework getClient()
    {
        CuratorFramework client= CuratorFrameworkFactory.builder()
                .connectString(CONN)
                .retryPolicy(new RetryNTimes(3,5000))
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .build();
        return client;
    }

}
