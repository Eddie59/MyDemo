package spring.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public IPerson person(){
        Person person=new Person();
        person.setName("eddie");
        person.setAge(18);
        return person;
    }

    @Bean(name = "logFactoryBean")
    public LogFactoryBean logFactoryBean(){
        LogFactoryBean logFactoryBean=new LogFactoryBean();
        logFactoryBean.setInterfaceName("spring.factorybean.IPerson");
        logFactoryBean.setTarget(person());
        return logFactoryBean;
    }

}
