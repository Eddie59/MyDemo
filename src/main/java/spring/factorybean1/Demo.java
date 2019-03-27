package spring.factorybean1;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    @Test
    public void go(){
        ApplicationContext context=new ClassPathXmlApplicationContext("spring/factorybean1/factorybean1.xml");
        Person person= context.getBean("person1",Person.class);
        System.out.println(person);
        person= context.getBean("person1",Person.class);
        System.out.println(person);
    }
}
