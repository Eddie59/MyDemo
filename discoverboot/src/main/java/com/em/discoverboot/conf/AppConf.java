package com.em.discoverboot.conf;

import com.em.discoverboot.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * AppConf class
 *
 * @author Administrator
 * @date
 */
@Configuration
@PropertySource(value = "classpath:resources.properties", ignoreResourceNotFound = false)
public class AppConf {
    @Autowired
    Environment environment;

    @Bean(value = "person1")
    public Person getPerson() {
        Person person = new Person();
        person.setName(environment.getProperty("person.name"));
        person.setSex(Integer.parseInt(environment.getProperty("person.sex")));
        return person;
    }




}
