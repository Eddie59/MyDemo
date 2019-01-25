package spring.factorybean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    @Test
    public void go() {
/*
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/factorybean/factorybean.xml");
        // FactoryBean是一个接口，当在IOC容器中的Bean实现了FactoryBean后，通过getBean(String BeanName)获取到的Bean对象并不是FactoryBean的实现类对象，
        // 而是这个实现类中的getObject()方法返回的对象
        // 要想获取FactoryBean的实现类，就要getBean(&BeanName)，在BeanName之前加上&。
        IPerson person = context.getBean("logFactoryBean", IPerson.class);
        person.sayHi();
        person.sayName();
        person.sayAge();
*/

        try {
            AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
  /*          LogFactoryBean logFactoryBean = context.getBean(LogFactoryBean.class);
            IPerson person=(IPerson)logFactoryBean.getObject();*/

            IPerson person = context.getBean("logFactoryBean", IPerson.class);
            person.sayHi();
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
}
