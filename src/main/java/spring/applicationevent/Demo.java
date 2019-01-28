package spring.applicationevent;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Demo {
    public static void main(String...args){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/applicationevent/spring-config.xml");
        EmailEvent emailEvent=new EmailEvent("this is event name","eddiesong@live.com","warning the moon");
        applicationContext.publishEvent(emailEvent);
    }
}
