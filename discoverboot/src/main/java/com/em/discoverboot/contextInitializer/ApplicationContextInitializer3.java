package com.em.discoverboot.contextInitializer;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * ApplicationContextInitializer3 class
 *
 * @author Administrator
 * @date
 */
public class ApplicationContextInitializer3 implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        System.out.println("ApplicationContextInitializer3 init configurableApplicationContext");
    }
}
