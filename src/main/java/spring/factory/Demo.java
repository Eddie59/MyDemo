package spring.factory;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.factory.factory.IFactory;
import spring.factory.factory.NanChangFactory;
import spring.factory.factory.ShangHaiFactory;
import spring.factory.model.YaBo;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {

    public static void main(String... args) {
//        IFactory factory = new ShangHaiFactory();
//        YaBo shYabo = factory.ProduceYaBo();
//        shYabo.Print();
//        YaJia shYaJia = factory.produceYaJia();
//        shYaJia.Print();
//
//        IFactory factory1 = new NanChangFactory();
//        YaBo ncYaBo = factory1.ProduceYaBo();
//        ncYaBo.Print();
//        YaJia ncYaJia = factory1.produceYaJia();
//        ncYaJia.Print();


        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/factory/factory.xml");

        IFactory factory = context.getBean("shangHaiFactory", ShangHaiFactory.class);
        YaBo yaBo = factory.ProduceYaBo();
        yaBo.Print();

        IFactory factory1 = context.getBean("nanChangFactory", NanChangFactory.class);
        YaBo yaBo1 = factory1.ProduceYaBo();
        yaBo1.Print();

    }

}
