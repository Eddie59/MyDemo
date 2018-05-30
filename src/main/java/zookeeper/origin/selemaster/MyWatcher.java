package zookeeper.origin.selemaster;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * MyWatcher class
 *
 * @author Administrator
 * @date
 */
public class MyWatcher implements Watcher {

    protected static  ZooKeeper zk=null;
//    protected static Integer mutex;
    int sessionTimeOut=5000;
    protected String root;

    public MyWatcher(String connectString) {
        if(zk == null){
            try {
                System.out.println("创建一个新的连接:");
                zk = new ZooKeeper(connectString, sessionTimeOut, this);
//                mutex = new Integer(-1);
            } catch (IOException e) {
                zk = null;
                System.out.println(e);
            }
        }
    }
    @Override
    synchronized public void process(WatchedEvent event) {
       /* synchronized (mutex) {
            mutex.notify();
        }*/


       try {


        Event.EventType type=event.getType();
        Event.KeeperState state = event.getState();
        String path=event.getPath();
        System.out.println(String.format("watche info type: %s stat: %s path: %s",type,state,path));

       if(type== Event.EventType.None&&state== Event.KeeperState.SyncConnected)
       {
           System.out.println("zk init cucess");
       }
       else if(type==Event.EventType.NodeDeleted&&path.equals(root + "/leader"))
       {
           System.out.println("znode delete need find master again");
           String connectString = "localhost:2181";
           LeaderElection le = new LeaderElection(connectString, "/GroupMembers");
           try {
               le.findLeader();
               Thread.sleep(Long.MAX_VALUE);
           } catch (Exception e) {
               System.out.println(e);
           }

       }
       else
       {
           System.out.println("watch not catch");
       }

       }
       catch (Exception exp)
       {
           System.out.println("proccess exp "+ exp);
       }

    }

}
