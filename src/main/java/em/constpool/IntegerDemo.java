package em.constpool;

import org.junit.Test;

/**
 * IntegerDemo class
 *
 * @author eddiesong
 * @date
 */
public class IntegerDemo {

    @Test
    public void demo1()
    {}

    /**
     * Byte,Short,Integer,Long,Character,Boolean都实现了常量池技术，但Float和Double并没有实现常量池。
     */
    @Test
    public void demo2()
    {
        Integer i1=10;//自动装箱为引用类型Integer，Integer进常量池
        Integer i2=10;
        System.out.println(i1==i2);//比较引用地址

        Integer i3=127;
        Integer i4=127;
        System.out.println(i3==i4);

        Integer i5=128;
        Integer i6=128;
        System.out.println(i5==i6);
        //Integer的equals比较的是值
        System.out.println(i5.equals(i6));

        Byte b1=12;
        Byte b2=12;
        System.out.println(b1==b2);

        Byte b3=127;
        Byte b4=127;
        System.out.println(b3==b4);
    }

    @Test
    public void demo3()
    {
        Integer i1=10;
        Integer i2=10;
        Integer i3=20;
        Integer i11=new Integer(10);
        Integer i22=new Integer(10);
        Integer i33=new Integer(20);
        System.out.println(i1==i2);
        System.out.println(i1==i11);
        System.out.println(i11==i22);
        //True 因为i1+i2会先拆箱后两者相加得20，
        // 然后i3==20时会再装箱，相当于Integer x=new Interger(20)，
        // 因为i3是Integer类型的20，已放到常量池里，所以直接指向了在常量池中的i3
        System.out.println(i3==(i1+i2));
        System.out.println(i3==(i11+i22));
        System.out.println(i33==(i1+i2));
        System.out.println(i33==(i11+i22));

    }
}
