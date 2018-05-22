package em.proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * HelloMethodInterceptor class
 * HelloMethodInterceptor是作为中间件，供多个代理类调用
 * @author Administrator
 * @date
 */
public class HelloMethodInterceptor implements MethodInterceptor {
    /**
     * @param o           调用方法的对象
     * @param method      调用的方法
     * @param objects     调用方法的参数
     * @param methodProxy 生成的代理类
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before:" + method.getName());
        Object object = methodProxy.invokeSuper(o, objects);
        System.out.println("After:" + method.getName());
        return object;
    }
}
