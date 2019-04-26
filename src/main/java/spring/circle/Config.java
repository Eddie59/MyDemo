package spring.circle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public CircleA circleA(){
        return new CircleA(circleB());
    }

    @Bean
    public CircleB circleB(){
        return new CircleB(circleC());
    }

    @Bean
    public CircleC circleC(){
        return new CircleC(circleA());
    }
}
