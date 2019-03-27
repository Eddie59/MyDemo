package spring.lookup;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    @Test
    public void Run()
    {
        ApplicationContext app= new ClassPathXmlApplicationContext("spring/lookup/LookupMethod.xml");

        FruitPlate fruitPlate0= (FruitPlate)app.getBean("fruitPlate0");
        System.out.println(fruitPlate0.getFruit());
        System.out.println(fruitPlate0.getFruit());
        System.out.println(fruitPlate0.getFruit());

        FruitPlate fruitPlate1= app.getBean("fruitPlate1",FruitPlate.class);
        System.out.println(fruitPlate1.getFruit());
        System.out.println(fruitPlate1.getFruit());
        System.out.println(fruitPlate1.getFruit());

        FruitPlate fruitPlate2= app.getBean("fruitPlate2",FruitPlate.class);
        System.out.println(fruitPlate2.getFruit());
        System.out.println(fruitPlate2.getFruit());
        System.out.println(fruitPlate2.getFruit());

    }
}
