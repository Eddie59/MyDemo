package zookeeper.origin.watcher;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    public static void main(String...args)
    {
        try {
            ActiveKeyValueStore keyValueStore=new ActiveKeyValueStore();
            keyValueStore.connect("127.0.0.1:2181");
            keyValueStore.write("/demowatcher","watchData");
            keyValueStore.write("/demowatcher/a","watchData1");
            keyValueStore.write("/demowatcher/b","watchData2");

            Thread.sleep(5000);
        }
        catch (Exception exp)
        {

        }


    }
}
