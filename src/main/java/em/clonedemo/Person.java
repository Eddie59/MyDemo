package em.clonedemo;

/**
 * Person class
 *
 * @author Administrator
 * @date
 */
public class Person implements Cloneable {

    private String name;
    private Person father;

    public Person(String _name) {
        this.name = _name;
    }

    public Person(String _name, Person _father) {
        this.name = _name;
        this.father = _father;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Person getFather() {
        return father;
    }

    public void setFather(Person father) {
        this.father = father;
    }

    @Override
    public Person clone() {
        Person per;
        try {
            //per=(Person)super.clone();//相当于调用Object.clone()方法,Object的clone方法是浅拷贝，意味着对象里面引用变量，拷贝的是引用的地址，而不是引用变量本身

            //深拷贝，把对象的信息复制一份，而且新建一个父亲节点
            per = new Person(this.name);
            per.father = new Person(this.getFather().getName());

        } catch (Exception exp) {
            per = null;
        }
        return per;
    }
}
