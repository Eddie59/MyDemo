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
