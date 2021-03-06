package spring.parent.impl;

import spring.parent.Axe;
import spring.parent.Person;

/**
 * American class
 *
 * @author Administrator
 * @date
 */
public class American implements Person {
    private String name;
    private Axe axe;

    public void setAxe(Axe axe) {
        this.axe = axe;
    }

    public Axe getAxe() {
        return axe;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void useAxe() {
        System.out.println("American say:" + axe.chop());
    }
}
