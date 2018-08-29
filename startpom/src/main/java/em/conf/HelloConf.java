package em.conf;

import em.properties.HelloProperty;
import em.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * HelloConf class
 *
 * @author Administrator
 * @date
 */
@Configuration
@EnableConfigurationProperties(HelloProperty.class)
public class HelloConf {

    @Autowired
    private HelloProperty helloProperty;

    @Bean
    public HelloService getHelloService() {
        HelloService helloService = new HelloService();
        helloService.setMsg(helloProperty.getMsg());
        return helloService;
    }
}
