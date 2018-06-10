package cn.tostringbuilder;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * User class
 *
 * @author Administrator
 * @date
 */
public class User {

    public static void main(String...args)
    {
        User u = new User();

        u.setAge(25);

        u.setName("zhengtian");

        //对象及其属性一行显示
        System.out.println(ToStringBuilder.reflectionToString(u));
        System.out.println(ToStringBuilder.reflectionToString(u, ToStringStyle.DEFAULT_STYLE));
        //属性换行显示
        System.out.println(ToStringBuilder.reflectionToString(u, ToStringStyle.MULTI_LINE_STYLE));
        //不显示属性名，只显示属性值，在同一行显示
        System.out.println(ToStringBuilder.reflectionToString(u,ToStringStyle.NO_FIELD_NAMES_STYLE));
        //不显示属性名，只显示属性值，在同一行显示
        System.out.println(ToStringBuilder.reflectionToString(u, ToStringStyle.NO_FIELD_NAMES_STYLE));
        //对象名称简写
        System.out.println(ToStringBuilder.reflectionToString(u, ToStringStyle.SHORT_PREFIX_STYLE));
        //只显示属性
        System.out.println(ToStringBuilder.reflectionToString(u, ToStringStyle.SIMPLE_STYLE));



    }

    private String name;

    private int age;


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
