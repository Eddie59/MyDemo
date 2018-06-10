package cn.tostringbuilder;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * MyClass class
 *
 * @author Administrator
 * @date
 */
public class MyClass {
    private String name = null;
    private int age = 0;

    public MyClass(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    public static void main(String...args)
    {
        MyClass one = new MyClass("Becker", 35);
        MyClass two = new MyClass("Becker", 35);
        MyClass three = new MyClass("Agassi", 33);

        System.out.println("One>>>" + one);
        System.out.println("Two>>>" + two);
        System.out.println("Three>>>" + three);

        //两个对象比较，只比较各属性的值，而不是引用
        System.out.println("one equals two? " + one.equals(two));
        System.out.println("one equals three? " + one.equals(three));

        System.out.println("One HashCode>>> " + one.hashCode());
        System.out.println("Two HashCode>>> " + two.hashCode());
        System.out.println("Three HashCode>>> " + three.hashCode());
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
