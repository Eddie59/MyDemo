package spring.lookup.demo;

import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import spring.lookup.Apple;
import spring.lookup.Banana;
import spring.lookup.FruitPlate;

/**
 * SpringConfig class
 *
 * @author Administrator
 * @date
 */
@Configuration
public class SpringConfig {

    @Bean
    @Scope("prototype")
    public Apple apple() {
        return new Apple();
    }

    @Bean
    @Scope("prototype")
    public Banana banana() {
        return new Banana();
    }

    @Bean
    @Lookup
    public FruitPlate getApple() {
        return null;
    }

}
