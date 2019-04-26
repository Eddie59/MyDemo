package spring.circle;

public class CircleB {
    private CircleC circleC;

    public CircleB() {
        System.out.println("构造CircleB");
    }

    public CircleB(CircleC circleC) {
        this.circleC = circleC;
    }

    public void setCircleC(CircleC circleC) {
        this.circleC = circleC;
    }

    public void b() {
        circleC.c();
    }
}
