package spring.applicationevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EmailLister2 implements ApplicationListener {
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("listener2");
    }
}
