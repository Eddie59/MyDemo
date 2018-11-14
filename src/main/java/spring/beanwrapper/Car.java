package spring.beanwrapper;

import java.util.Arrays;

/**
 * Car class
 *
 * @author Administrator
 * @date
 */
public class Car {

    private String name;
    private Wheel[] wheels;
    private Driver driver;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Wheel[] getWheels() {
        return wheels;
    }

    public void setWheels(Wheel[] wheels) {
        this.wheels = wheels;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", wheels=" + Arrays.toString(wheels) +
                ", driver=" + driver +
                '}';
    }
}
