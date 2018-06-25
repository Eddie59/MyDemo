package spring.configuration.test;

import org.springframework.stereotype.Service;

/**
 * TestBean1 class
 *
 * @author Administrator
 * @date
 */
@Service(value = "TestBean1")
public class TestBean1 {
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
