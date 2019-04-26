package spring.circle;

public class CircleA {

    private CircleB circleB;

    public CircleA() {
        System.out.println("构造CircleA");
    }

    /**
     * 构造器注入
     * @param circleB
     */
    public CircleA(CircleB circleB) {
        this.circleB = circleB;
    }

    /**
     * set注入
     * @param circleB
     */
    public void setCircleB(CircleB circleB) {
        this.circleB = circleB;
    }

    public void a() {
        circleB.b();
    }
}
