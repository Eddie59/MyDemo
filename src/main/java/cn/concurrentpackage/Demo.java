package cn.concurrentpackage;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

public class Demo {

    @Test
    public void run1() throws Exception {
        ArrayBlockingQueue queue = new ArrayBlockingQueue(1);
        queue.put("a");
        queue.put("b");
        System.out.println(queue);
    }

}
