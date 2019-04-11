package spring.transaction.iswork;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyHandler implements InvocationHandler {

    private UserService userService;
    public MyHandler(UserService _userservice){
        this.userService=_userservice;
    }

    /**
     *  调用userService实例的method方法
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //method.getName().startsWith("txMethod")这个相当于取出方法的注解，看是否有@Transaction注解
        if(method.getName().startsWith("txMethod")){
            System.out.println("开启事务");
        }

        //调用userService对象的method方法
        method.invoke(userService,null);

        if(method.getName().startsWith("txMethod")){
            System.out.println("提交事务");
        }
        return null;
    }
}
