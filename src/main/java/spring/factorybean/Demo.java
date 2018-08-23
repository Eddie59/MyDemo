package spring.factorybean;

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
        ApplicationContext context=new ClassPathXmlApplicationContext("spring/factorybean/factorybean.xml");
        IPerson person= context.getBean("logFactoryBean",IPerson.class);
        person.sayHi();
    }
}
