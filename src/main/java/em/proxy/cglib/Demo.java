package em.proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    public static void main(String...args)
    {
        //代理类对象是由Enhancer类创建的，Enhancer是CGLIB的字节码增强器，可以对类进行拓展
        Enhancer enhancer=new Enhancer();
        //让代理类继承被代理的类
        enhancer.setSuperclass(HelloWorld.class);
        //设置拦截类，当调用被代理类的方法时，会先调用拦截类的方法，然后由拦截类去调用被代理的类的方法
        enhancer.setCallback(new HelloMethodInterceptor());

        //生成第一个代理类，多个代理类调用中间方法
        HelloWorld helloWorld1=(HelloWorld) enhancer.create();
        helloWorld1.sayHello();
        //生成第二个代理类
        HelloWorld helloWorld2=(HelloWorld)enhancer.create();
        helloWorld2.sayHello();

    }
}
