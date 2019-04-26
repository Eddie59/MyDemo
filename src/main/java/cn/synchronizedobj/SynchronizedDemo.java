package cn.synchronizedobj;

/**
 * javap -c ***.class
 */
public class SynchronizedDemo {

    public void method() {
        synchronized (this) {
            System.out.println("start");
        }
    }


}
