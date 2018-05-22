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

        FruitPlate fruitPlate1= app.getBean("fruitPlate1",FruitPlate.class);
        Apple app1=(Apple)fruitPlate1.getFruit();
        Apple app2=(Apple)fruitPlate1.getFruit();

        FruitPlate fruitPlate2= app.getBean("fruitPlate2",FruitPlate.class);
        fruitPlate2.getFruit();

    }
}
