package spring.parent;

import spring.parent.impl.American;
import spring.parent.impl.Chinese;
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
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:parent.xml");
        Person chinese = context.getBean("chinese", Chinese.class);
        chinese.useAxe();

        Person american =context.getBean("american", American.class);
        american.useAxe();
    }
}
