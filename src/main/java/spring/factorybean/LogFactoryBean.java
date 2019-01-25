package spring.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.lang.Nullable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * LogFactoryBean class
 *
 * @author Administrator
 * @date
 */
public class LogFactoryBean implements FactoryBean<Object> {
    /**
     * 接口名
     */
    private String interfaceName;
    /**
     * 代理对象
     */
    private Object target;
    /**
     * 生成的代理对象
     */
    private Object proxyObj;

    @Nullable
    @Override
    public Object getObject() throws Exception {

        System.out.println("getObject");

        //要在target的所有方法前后加上Log
        //那么不可能直接使用target实例，所以要使用生成一个target的代理对象，在代理中调用target的方法，在方法前后输出日志
        //调用app.GetBean()时，会返回一个target的代理对象，调用代理对象的方法时，会调用下面的invoke
        proxyObj = Proxy.newProxyInstance(
                this.getClass().getClassLoader(),
                new Class[]{Class.forName(interfaceName)},
                new InvocationHandler() {
                    /**
                     *
                     * @param proxy 就是生成的person对象的代理对象proxyObj
                     * @param method 要执行的方法
                     * @param args 方法的参数
                     * @return
                     * @throws Throwable
                     */
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("Method before...");
                        //调用代理对象的method方法,实际上执行的是person对象的方法，参数为args
                        Object result = method.invoke(target, args);
                        System.out.println("Method after...");
                        return result;
                    }
                });
        return proxyObj;
    }

    @Nullable
    @Override
    public Class<?> getObjectType() {
        return proxyObj == null ? target.getClass() : proxyObj.getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }
}
