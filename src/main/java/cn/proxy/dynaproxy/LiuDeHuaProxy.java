package cn.proxy.dynaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * LiuDeHuaProxy class
 *
 * @author Administrator
 * @date
 */
public class LiuDeHuaProxy {

    /**
     * 设计一个类变量记住代理类要代理的目标对象
     */
    private Person ldh = new LiuDeHua();

    /**
     * @return 这个方法返回刘德华的代理对象：Person person = LiuDeHuaProxy.getProxy();得到一个代理对象
     */
    public Person getProxy() {
        Object obj = Proxy.newProxyInstance(
                LiuDeHua.class.getClassLoader(),
                ldh.getClass().getInterfaces(),
                /**
                 33                      * InvocationHandler接口只定义了一个invoke方法，因此对于这样的接口，我们不用单独去定义一个类来实现该接口，
                 34                      * 而是直接使用一个匿名内部类来实现该接口，new InvocationHandler() {}就是针对InvocationHandler接口的匿名实现类
                 37                      * 在invoke方法编码指定返回的代理对象干的工作
                 38                      * proxy : 把代理对象自己传递进来
                 39                      * method：把代理对象当前调用的方法传递进来
                 40                      * args:把方法参数传递进来
                 41                      *
                 42                      * 当调用代理对象的person.sing("冰雨");或者 person.dance("江南style");方法时，
                 43                      * 实际上执行的都是invoke方法里面的代码，
                 44                      * 因此我们可以在invoke方法中使用method.getName()就可以知道当前调用的是代理对象的哪个方法
                 45                      */
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if (method.getName() == "sing") {
                            //已经给钱了，经纪人自己不会唱歌，就只能找刘德华去唱歌！
                            System.out.println("我是他的经纪人，要找他唱歌得先给十万块钱！！");
                            //代理对象调用真实目标对象的sing方法去处理用户请求
                            return method.invoke(ldh, args);
                        }
                        if (method.getName() == "dance") {
                            System.out.println("我是他的经纪人，要找他跳舞得先给二十万块钱！！");
                            return method.invoke(ldh, args);
                        }
                        return null;
                    }
                });

//        生成的代理类是继承了Person接口，所以可以强类型转换
        return (Person) obj;
    }
}
