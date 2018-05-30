package zookeeper.origin.watcher;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

import java.nio.charset.Charset;

/**
 * ActiveKeyValueStore class
 *
 * @author Administrator
 * @date
 */
public class ActiveKeyValueStore extends ConnectionWatcher {
    private static final Charset CHARSET = Charset.forName("UTF-8");

    public void write(String path, String value) throws KeeperException, InterruptedException {
        Stat stat = null;
        try {
            //在这里注册watcher，true表示使用默认的watcher，就是新建Zookeeper对象时设置的watcher
            stat = zk.exists(path, true);
            if (stat == null) {
                zk.create(path, value.getBytes(CHARSET), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            } else {
                zk.setData(path, value.getBytes(CHARSET), -1);
            }
            System.out.println("write success");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String read(String path, Watcher watch) throws KeeperException, InterruptedException {
        byte[] data = zk.getData(path, watch, null);
        return new String(data, CHARSET);
    }

    public void delete(String path) throws InterruptedException, KeeperException {
        zk.delete(path, -1);
        System.out.println("delete success");
    }

}
