package cn.clonedemo;

/**
 * demo class
 *
 * @author Administrator
 * @date
 */
public class demo {
    public static void main(String...args)
    {
        Person father=new Person("father name");
        Person son=new Person("son name",father);

        Person xiao=son.clone();

        xiao.setName("xiao");

        System.out.println(son.getName()+" "+son.getFather().getName());
        System.out.println(xiao.getName()+" "+xiao.getFather().getName());

        son.getFather().setName("gan");

        System.out.println(son.getName()+" "+son.getFather().getName());
        System.out.println(xiao.getName()+" "+xiao.getFather().getName());

    }
}
