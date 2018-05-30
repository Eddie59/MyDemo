package zookeeper.curator.leaderselect;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
/**
 * Curator framework's leader election test.
 * Output:
 * LeaderSelector-2 take leadership!
 * LeaderSelector-2 relinquish leadership!
 * LeaderSelector-1 take leadership!
 * LeaderSelector-1 relinquish leadership!
 * LeaderSelector-0 take leadership!
 * LeaderSelector-0 relinquish leadership!
 * ...
 */

/**
 * LeaderSelect2 class
 *
 * @author Administrator
 * @date
 */
public class LeaderSelect2 extends LeaderSelectorListenerAdapter implements Closeable {
    private final String name;
    private final LeaderSelector leaderSelector;


    public LeaderSelect2(CuratorFramework client, String path, String name) {
        this.name = name;

        leaderSelector = new LeaderSelector(client, path, this);
        leaderSelector.autoRequeue();
    }

    public void start() throws IOException {
        leaderSelector.start();
    }

    @Override
    public void close() throws IOException {
        leaderSelector.close();
    }

    @Override
    public void takeLeadership(CuratorFramework client) throws Exception {
        final int waitSeconds = (int) (5 * Math.random()) + 1;
        System.out.println(name + " is now the leader. Waiting " + waitSeconds + " seconds...");
        try {
            //模拟系统做的事，如果系统异常，会被catch到；如果机器崩溃，会从zk中删除master接点，再次选举master节点
            Thread.sleep(TimeUnit.SECONDS.toMillis(waitSeconds));
        } catch (InterruptedException e) {
            System.err.println(name + " was interrupted.");
            Thread.currentThread().interrupt();
        } finally {
            System.out.println(name + " relinquishing leadership.\n");
        }
    }

}
