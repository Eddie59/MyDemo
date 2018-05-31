package em.reentrantlock;

/**
 * BoundedBufferDemo class
 *
 * @author Administrator
 * @date
 */
public class BoundedBufferDemo extends Thread {
    private BoundedBuffer boundedBuffer = new BoundedBuffer();
    private String name;

    public BoundedBufferDemo(BoundedBuffer boundedBuffer, String name) {
        super(name);
        this.boundedBuffer = boundedBuffer;
        this.name = name;
    }

    @Override
    public void run() {
        super.run();
        System.out.println(Thread.currentThread().getName() + " is running!");
        if (name.startsWith("PUT")) {
            for (int i = 1; i < 4; i++) {
                try {
                    boundedBuffer.put(i);
                    System.out.printf("--PUT-- %s has put %d into the buffer.\n", Thread.currentThread().getName(), i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else if (name.startsWith("TAKE")) {
            for (int i = 1; i < 4; i++) {
                try {
                    int value = boundedBuffer.take();
                    System.out.printf("--TAK-- %s has took %d from the buffer.\n", Thread.currentThread().getName(),
                            value);
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public static void main(String[] args) {
        BoundedBuffer boundedBuffer = new BoundedBuffer();
        // 创建3个put线程，每个往Buffer里put 3次
        BoundedBufferDemo put1 = new BoundedBufferDemo(boundedBuffer, "PUT1");
        BoundedBufferDemo put2 = new BoundedBufferDemo(boundedBuffer, "PUT2");
        BoundedBufferDemo put3 = new BoundedBufferDemo(boundedBuffer, "PUT3");

        // 创建2个take线程，每个从Buffer里take 3次
        BoundedBufferDemo take1 = new BoundedBufferDemo(boundedBuffer, "TAKE1");
        BoundedBufferDemo take2 = new BoundedBufferDemo(boundedBuffer, "TAKE2");

        put1.start();
        put2.start();
        put3.start();
        take1.start();
        take2.start();
    }

}
