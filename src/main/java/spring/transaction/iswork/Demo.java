package spring.transaction.iswork;

import java.lang.reflect.Proxy;

public class Demo {
    public static void main(String... args) {
        UserService userService = new UserServiceImpl();

        Object newProxyInstance = Proxy.newProxyInstance(
                userService.getClass().getClassLoader(),
                new Class[]{UserService.class},
                new MyHandler(userService));

        UserService userServiceProxy = (UserService) newProxyInstance;
        userServiceProxy.txMethod();
        System.out.println("======");
        userServiceProxy.txMethod2();
        System.out.println("======");
        userServiceProxy.noTxMethod();
    }
}
