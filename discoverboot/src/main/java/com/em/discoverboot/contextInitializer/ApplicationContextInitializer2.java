package com.em.discoverboot.contextInitializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ApplicationContextInitializer2 class
 *
 * @author Administrator
 * @date
 */
public class ApplicationContextInitializer2 implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("ApplicationContextInitializer2 init configurableApplicationContext");
    }
}
