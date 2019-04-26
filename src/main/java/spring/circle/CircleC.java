package spring.circle;

public class CircleC {
    private CircleA circlea;

    public CircleC() {
        System.out.println("构造CircleC");
    }

    public CircleC(CircleA circlea) {
        this.circlea = circlea;
    }

    public void setCircleA(CircleA circlea) {
        this.circlea = circlea;
    }

    public void c() {
        circlea.a();
    }
}
