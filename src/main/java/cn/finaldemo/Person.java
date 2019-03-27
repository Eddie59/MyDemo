package cn.finaldemo;

import org.junit.Test;

import java.lang.reflect.Field;

public class Person {
    public static int age;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Test
    public void demo(){
        Class<Person> personClass = Person.class;
        //有age、name
        Field[] fields = personClass.getDeclaredFields();
        //只有age
        Field[] fields1 = personClass.getFields();
        System.out.println(fields);
    }
}
