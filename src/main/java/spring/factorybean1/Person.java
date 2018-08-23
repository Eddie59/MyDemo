package spring.factorybean1;

import spring.factorybean.IPerson;

/**
 * Person class
 *
 * @author Administrator
 * @date
 */
public class Person {
    private String name;
    private int age;

    public void sayHi(){
        System.out.println("Hi");
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
