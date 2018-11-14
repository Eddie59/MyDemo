package cn.decorator.shape;

/**
 * Rectangle class
 *
 * @author Administrator
 * @date
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
