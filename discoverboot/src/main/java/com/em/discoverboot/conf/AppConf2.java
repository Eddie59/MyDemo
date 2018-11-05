package com.em.discoverboot.conf;

import com.em.discoverboot.model.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;


/**
 * AppConf2 class
 *
 * @author Administrator
 * @date
 */
@Configuration
public class AppConf2 {
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() throws Exception {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        PropertySource propertySource = new ResourcePropertySource("ResourcePropertySource", "classpath:/spring/environment/conf.properties");
//        configurer.setPropertySources(propertySource);
        return configurer;
    }

    @Bean(value = "person2")
    public Person getPerson() {
        Person person = new Person();
        person.setName("abc ${person.name}");
        person.setSex(19);
        return person;
    }
}
