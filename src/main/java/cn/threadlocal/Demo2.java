package cn.threadlocal;

/**
 * Demo2 class
 *
 * @author Administrator
 * @date
 */
public class Demo2 {

    private static NumberList numlist = new NumberList();

    private static ThreadLocal<NumberList> local = new ThreadLocal<NumberList>() {
        @Override
        protected NumberList initialValue() {
            //对象是引用类型，所以每个线程的副本还是指向它的引用
            return numlist;
            ////每次新建一个对象，所以线程的副本是另一个新的对象
            //return new NumberList();
        }
    };

    public static void main(String[] args) {
        Thread[] threads = new Thread[3];
        for (int i = 0; i < 3; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    NumberList list = local.get();
                    for (int j = 0; j < 1000; j++) {
                        list.init();
                    }
                    local.set(list);
                    System.out.println(Thread.currentThread().getName() + "===" + local.get().num);
                }
            });
        }
        for (Thread t : threads) {
            t.start();
        }
    }

    static class NumberList {
        int num;
        public void init() {
            num++;
        }
    }
}
