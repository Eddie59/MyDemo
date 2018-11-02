package spring.lookup.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import spring.lookup.FruitPlate;

/**
 * Test class
 *
 * @author Administrator
 * @date
 */
public class Test {
    public static void main(String...args){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(SpringConfig.class);
        FruitPlate plate= context.getBean(FruitPlate.class);
        System.out.println(plate.getApple());
        System.out.println(plate.getApple());
        System.out.println(plate.getApple());
        System.out.println(plate.getApple());
    }
}
