package cn.waitnotify;

/**
 * WaitNotifyTest class
 *
 * @author Administrator
 * @date
 */
public class WaitNotifyTest {

    /**
     * 在多线程间共享的对象上使用wait
     */
    private String[] shareObj = { "true" };


    class ThreadWait extends Thread {
        public ThreadWait(String name){
            //线程的名字
            super(name);
        }

        @Override
        public void run() {
           synchronized (shareObj) {
                while ("true".equals(shareObj[0])) {
                    System.out.println("线程"+ this.getName() + "开始等待");
                    long startTime = System.currentTimeMillis();
                    try {
                        //当前线程移到Obj的阻塞队列中，然后交出obj的控制权，让其它线程使用
                        shareObj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    long endTime = System.currentTimeMillis();
                    System.out.println("线程" + this.getName() + "等待时间为：" + (endTime - startTime));
                }
            }
            System.out.println("线程" + getName() + "等待结束");
        }
    }


    class ThreadNotify extends Thread {
        public ThreadNotify(String name){
            super(name);
        }
        @Override
        public void run() {
            try {
                // 给等待线程等待时间
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (shareObj) {
                System.out.println("线程" + this.getName() + "开始准备通知");
                shareObj[0] = "false";
                //通知所有在ob的阻塞队列的线程，把所有的线程移到obj的就绪队列中，等待执行，执行完notifyAll并不会立即释放锁，
                //必须等到synchronized执行完后释放锁，唤醒的线程才会获取锁，去执行
                shareObj.notifyAll();
                System.out.println("线程" + this.getName() + "通知结束");
            }
            System.out.println("线程" + this.getName() + "运行结束");
        }
    }




    public static void main(String[] args) {
        WaitNotifyTest test = new WaitNotifyTest();
        ThreadWait threadWait1 = test.new ThreadWait("wait thread1");
        threadWait1.setPriority(2);
        ThreadWait threadWait2 = test.new ThreadWait("wait thread2");
        threadWait2.setPriority(3);
        ThreadWait threadWait3 = test.new ThreadWait("wait thread3");
        threadWait3.setPriority(4);

        ThreadNotify threadNotify = test.new ThreadNotify("notify thread");
        threadNotify.start();

        threadWait1.start();
        threadWait2.start();
        threadWait3.start();
    }
}

