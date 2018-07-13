package cn.javaio.objreferenceserializable;

/**
 * Person class
 * 没有实现Serializabler接口,在写入ObjectOutputStream时，报没有实现Serializable异常
 *
 * @author Administrator
 * @date
 */
public class Person {
    private String name;
    private int age;

    public Person(String _name, int _age) {
        this.name = _name;
        this.age = _age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
