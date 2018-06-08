package spring.methodInvokingFactoryBean;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Properties;

/**
 * StaticFuncRtn class
 *
 * @author Administrator
 * @date
 */
public class StaticFuncRtn {

    @Test
    public void Run() {
        ApplicationContext app = new ClassPathXmlApplicationContext("spring/methodInvokingFactoryBean/spring-config.xml");

        Properties properties= java.lang.System.getProperties();
        String version= properties.getProperty("java.version");
        System.out.println(version);

        Properties sysProps = app.getBean("staticFunc1", Properties.class);
        System.out.println(sysProps.getProperty("java.version"));

        sysProps = app.getBean("staticFunc2", Properties.class);
        System.out.println(sysProps.getProperty("java.version"));

        String javaVersion = app.getBean("javaVersion", String.class);
        System.out.println(javaVersion);

        Son son2 = app.getBean("son2", spring.methodInvokingFactoryBean.Son.class);
        System.out.println(son2.getAge());


    }

}
