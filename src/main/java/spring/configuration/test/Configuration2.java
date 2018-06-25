package spring.configuration.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration2 class
 *
 * @author Administrator
 * @date
 */
@Configuration
@ComponentScan(basePackages = "spring.configuration.test")
public class Configuration2 {

    public static void main(String[] args) {
        ApplicationContext context=new AnnotationConfigApplicationContext(Configuration2.class);
        TestBean1 testBean1= context.getBean(TestBean1.class);
        TestBean2 testBean2=context.getBean(TestBean2.class);
        testBean1.sayHello();
        testBean2.sayHello();
    }


}
