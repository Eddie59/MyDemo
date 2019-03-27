package com.em.discoverboot;

import com.em.discoverboot.contextInitializer.ApplicationContextInitializer1;
import com.em.discoverboot.model.Person;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;

import java.util.*;

@SpringBootApplication
public class DiscoverbootApplication {

    public static void main(String[] args) {

        Package aPackage = DiscoverbootApplication.class.getPackage();
        System.out.println(aPackage.getName());

        SpringApplication application = new SpringApplication(DiscoverbootApplication.class);
        application.addInitializers(new ApplicationContextInitializer1());
        ConfigurableApplicationContext applicationContext = application.run(args);

        Properties properties = System.getProperties();
        Map<String, String> mapEnv = System.getenv();

        Environment environment = new StandardEnvironment();
        Environment environment1= applicationContext.getEnvironment();

        Set<Person> set=new HashSet<Person>();
        set.add(new Person("1","eddie",12));

    }
}



