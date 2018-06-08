package spring.propertyeditor;

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
    public void run()
    {
        try {
            ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/propertyeditor/spring-config.xml");
            Entity entity= context.getBean("entity",Entity.class);
            System.out.println(entity.getDate());
        }
        catch (Exception exp)
        {
            System.out.println(exp);
        }

    }
}
