package spring.factorybean;

/**
 * Person class
 *
 * @author Administrator
 * @date
 */
public class Person implements IPerson {
    private String name;
    private int age;

    @Override
    public void sayHi(){
        System.out.println("Hi its person instance");
    }

    @Override
    public void sayAge() {
        System.out.println("My age is "+this.age);
    }

    @Override
    public void sayName() {
        System.out.println("My name is "+this.name);
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
