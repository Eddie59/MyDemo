package spring.beanwrapper;

/**
 * Wheel class
 *
 * @author Administrator
 * @date
 */
public class Wheel {
    private String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Wheel{" +
                "position='" + position + '\'' +
                '}';
    }
}
