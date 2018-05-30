package zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;

/**
 * CuratorClientTest class
 *
 * @author Administrator
 * @date
 */
public class CuratorClientTest {
    /** Zookeeper info */
    private static final String ZK_ADDRESS = "127.0.0.1:2181";
    private static final String ZK_PATH = "/zktest";

    public static void main(String[] args) throws Exception {
        //10为最大重试的次数，5000的消耗的时间
        CuratorFramework client= CuratorFrameworkFactory.newClient(ZK_ADDRESS,new RetryNTimes(10, 5000));
        client.start();
        System.out.println("zookeeper client start successfully!");

        String data1 = "hello";
        print("create", ZK_PATH, data1);
        client.create().creatingParentsIfNeeded().forPath(ZK_PATH,data1.getBytes());


        // 2.2 Get node and data
        print("ls", "/");
        print(client.getChildren().forPath("/"));
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));

        // 2.3 Modify data
        String data2 = "world";
        print("set", ZK_PATH, data2);
        client.setData().forPath(ZK_PATH, data2.getBytes());
        print("get", ZK_PATH);
        print(client.getData().forPath(ZK_PATH));

        // 2.4 Remove node
        print("delete", ZK_PATH);
        client.delete().forPath(ZK_PATH);
        print("ls", "/");
        print(client.getChildren().forPath("/"));

    }

    private static void print(String... cmds) {
        StringBuilder text = new StringBuilder("$ ");
        for (String cmd : cmds) {
            text.append(cmd).append(" ");
        }
        System.out.println(text.toString());
    }

    private static void print(Object result) {
        System.out.println(
                result instanceof byte[]
                        ? new String((byte[]) result)
                        : result);
    }
}
