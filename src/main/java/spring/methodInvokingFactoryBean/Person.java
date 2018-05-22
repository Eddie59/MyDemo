package spring.methodInvokingFactoryBean;

/**
 * Person class
 *
 * @author Administrator
 * @date
 */
public class Person {
    private int age;
    private Son son;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Son getSon() {
        return son;
    }

    public void setSon(Son son) {
        this.son = son;
    }
}
