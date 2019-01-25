package spring.beanfactory;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

public class Demo {

    @Test
    public void run(){
        ClassPathResource resource = new ClassPathResource("spring/beanfactory/spring-config.xml");
        BeanFactory beanFactory=new XmlBeanFactory(resource);
        Person person = beanFactory.getBean(Person.class);

    }

}
