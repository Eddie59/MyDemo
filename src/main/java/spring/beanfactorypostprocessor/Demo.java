package spring.beanfactorypostprocessor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    public static void main(String... args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/beanfactorypostprocessor/spring-config.xml");
        Car car = context.getBean(Car.class);
        System.out.println(car.getName());
    }
}
