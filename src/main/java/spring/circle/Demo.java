package spring.circle;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {

    @Test
    public void run1(){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);
        CircleA circleA = context.getBean(CircleA.class);
        System.out.println(circleA);
    }

    @Test
    public void run2(){
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("spring/circle/application.xml");
        CircleA circleA = context.getBean(CircleA.class);
        System.out.println(circleA);
    }


}
