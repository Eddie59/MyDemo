package spring.configuration.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;

/**
 * TestConfiguration class
 *
 * @author Administrator
 * @date
 */
@Configuration
public class Configuration1 {

    public Configuration1() {
        System.out.println("TestConfiguration容器启动初始化。。。");
    }

    @Bean(name = "testBean1", initMethod = "start", destroyMethod = "cleanUp")
    @Scope(value = "prototype")
    public TestBean testBean() {
        return new TestBean();
    }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Configuration1.class);

        TestBean testBean1 = context.getBean("testBean1", TestBean.class);
        testBean1.sayHello();
        System.out.println(testBean1);

        TestBean testBean2 = context.getBean("testBean1", TestBean.class);
        testBean2.sayHello();
        System.out.println(testBean2);
    }
}
