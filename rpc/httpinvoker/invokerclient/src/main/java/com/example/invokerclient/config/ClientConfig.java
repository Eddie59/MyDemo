package com.example.invokerclient.config;

import com.example.invokerclient.service.SpitterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;

/**
 * ClientConfig class
 *
 * @author Administrator
 * @date
 */
@Configuration
public class ClientConfig {

    @Bean
    public HttpInvokerProxyFactoryBean spitterService(){
        HttpInvokerProxyFactoryBean proxyFactoryBean=new HttpInvokerProxyFactoryBean();
        proxyFactoryBean.setServiceInterface(SpitterService.class);
//        proxyFactoryBean.setServiceUrl("http://localhost:8080/spitter/spitter.service");
        proxyFactoryBean.setServiceUrl("http://localhost:8080/spitter.service");
        return proxyFactoryBean;
    }

}
