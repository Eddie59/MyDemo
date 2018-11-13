package spring.beanfactorypostprocessor;

/**
 * Car class
 *
 * @author Administrator
 * @date
 */
public class Car {
    private String name;
    private String color;
    private double price;

    public Car() {
        System.out.println("调用了Car的构造函数，实例化了Car.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
