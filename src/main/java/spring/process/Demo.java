package spring.process;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Demo {
    @Test
    public void run(){
        ApplicationContext applicationContext=new AnnotationConfigApplicationContext(Config.class);
        Person person = applicationContext.getBean("007",Person.class);
    }
}
