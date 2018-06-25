package spring.configuration.test;

import org.springframework.stereotype.Service;

/**
 * TestBean2 class
 *
 * @author Administrator
 * @date
 */
@Service(value = "TestBean2")
public class TestBean2 {
    private String username;
    private String url;
    private String password;

    public void sayHello() {
        System.out.println("TestBean1 sayHello...");
    }

    public void start() {
        System.out.println("TestBean1 初始化。。。");
    }

    public void cleanUp() {
        System.out.println("TestBean1 销毁。。。");
    }
}
