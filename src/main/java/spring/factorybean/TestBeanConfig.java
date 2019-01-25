package spring.factorybean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestBeanConfig {

    @Autowired
    private LogFactoryBean logFactoryBean;

    public void test(){
        try{
           Person p= (Person)logFactoryBean.getObject();
           p.sayHi();
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
