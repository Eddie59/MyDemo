package cn.concurrentpackage;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicTest {

    @Test
    public void run() {
        final int loopcount = 10000;
        int threadcount = 10;

        final NonSafeSeq seql = new NonSafeSeq();
        final SafeSeq safeSeq = new SafeSeq();

        final CountDownLatch l = new CountDownLatch(threadcount);
        for (int i = 0; i < threadcount; i++) {
            final int index = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < loopcount; j++) {
                        seql.inc();
                        safeSeq.inc();
                    }
                    System.out.println("Finished: " + index);
                    l.countDown();
                }
            }).start();
        }

        try {
            l.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Both have finished...");
        System.out.println("NonSafeSeq:" + seql.get());
        System.out.println("SageSeq:" + safeSeq.get());
    }

    class NonSafeSeq {
        private long count = 0;

        public void inc() {
            count++;
        }

        public long get() {
            return count;
        }
    }

    class SafeSeq {
        private AtomicLong count = new AtomicLong(0);

        public void inc() {
            count.incrementAndGet();
        }

        public Long get() {
            return count.longValue();
        }
    }

}
