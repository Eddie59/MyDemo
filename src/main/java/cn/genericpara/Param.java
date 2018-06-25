package cn.genericpara;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Param class
 *
 * @author Administrator
 * @date
 */
public class Param<T1, T2> {
    class A {
    }

    class B extends A {
    }

    private Class<T1> entityClass;

    public void Demo() {
        System.out.println("getClass() == " + getClass());
        //返回表示此 Class 所表示的实体（类、接口、基本类型或 void）的直接超类的 Type，然后将其转换ParameterizedType。
        Type type = getClass();
//        Type type = getClass().getGenericSuperclass();
        System.out.println("type = " + type);

        //返回表示此类型实际类型参数的 Type 对象的数组。[0]就是这个数组中第一个了。简而言之就是获得超类的泛型参数的实际类型。
        Type trueType = ((ParameterizedType)type).getActualTypeArguments()[0];
        System.out.println("trueType1 = " + trueType);
        trueType = ((ParameterizedType)type).getActualTypeArguments()[1];
        System.out.println("trueType2 = " + trueType);

        this.entityClass = (Class<T1>)trueType;
        System.out.println("entityClass = " + entityClass);


        B t = new B();
        type = t.getClass().getGenericSuperclass();
        System.out.println( ((ParameterizedType)type).getActualTypeArguments().length );

    }
}
