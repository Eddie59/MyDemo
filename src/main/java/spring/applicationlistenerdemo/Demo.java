package spring.applicationlistenerdemo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class Demo {
    public static void main(String...args){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring/applicationevent/spring-config.xml");
    }
}
