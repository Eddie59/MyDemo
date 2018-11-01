package com.em.discoverboot.contextInitializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ApplicationContextInitializer1 class
 *
 * @author Administrator
 * @date
 */
public class ApplicationContextInitializer1 implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("ApplicationContextInitializer1 init configurableApplicationContext");
    }
}
