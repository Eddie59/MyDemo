package cn.concurrentpackage.retval;

import java.util.Random;
import java.util.concurrent.Callable;

public class RetThread implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(5000);
        Random random=new Random();
        return String.valueOf(random.nextInt());
    }
}
