package spring.lifecycle;

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

//        Resource res=new ClassPathResource("PersonConfig.xml");
//        MyBeanFactory bf=new XmlBeanFactory(res);
//        ((ConfigurableBeanFactory)bf).addBeanPostProcessor(new MyInstantiationAwareBeanPostProcessor());

        ApplicationContext bf=new ClassPathXmlApplicationContext("spring/lifecycle/PersonConfig.xml");

        Person per1 = bf.getBean(Person.class);
        System.out.println(per1.getGirlFriend());

        Person per2 = bf.getBean(Person.class);
        System.out.println(per1.getGirlFriend());




    }
}
