package spring.process;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean(name = "007",initMethod = "myInit")
    public Person person(){
        Person person=new Person();
        person.setName("eddie");
        person.setAge(18);
        return person;
    }
/*    @Bean(name = "008")
    public Person person8(){
        Person person=new Person();
        person.setName("eddie8");
        person.setAge(88);
        return person;
    }*/

    @Bean
    public PostProcessor postProcessor(){
        return new PostProcessor();
    }

    @Bean
    public MyInstantiationAwareBeanPostProcessor instantiationAwareBeanPostProcessor(){
        return new MyInstantiationAwareBeanPostProcessor();
    }
}
