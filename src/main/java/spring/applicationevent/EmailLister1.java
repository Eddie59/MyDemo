package spring.applicationevent;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class EmailLister1 implements ApplicationListener {
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("listener1 "+applicationEvent);
    }
}
