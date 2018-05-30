package zookeeper.origin.watcher;

import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * ConnectionWatcher class
 *
 * @author Administrator
 * @date
 */
public class ConnectionWatcher implements Watcher {
    private static final int SESSION_TIMEOUT=60000;

    protected ZooKeeper zk;
    CountDownLatch countDownLatch=new CountDownLatch(1);

    public void connect(String host)throws IOException, InterruptedException{
        zk=new ZooKeeper(host,SESSION_TIMEOUT,this);

    }

    @Override
    public void process(WatchedEvent event) {

        String path=event.getPath();
        Event.KeeperState state = event.getState();
        Event.EventType type = event.getType();
        System.out.println("监听中"+path+"\t"+state+"\t"+type);


      /*  //在这里注册watcher，true表示使用默认的watcher，就是新建Zookeeper对象时设置的watcher
       try {
           if(!StringUtils.isEmpty(path))
           {
               zk.exists(path,true);
           }
       }
       catch (Exception exp)
       {
           System.out.println(exp);
       }*/


    }

    public void close() throws InterruptedException{
        zk.close();
    }

}
