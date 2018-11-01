package com.em.springrpcclient.config;


import com.em.springrpcclient.services.SpitterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;

/**
 * ClientConfig class
 *
 * @author Administrator
 * @date
 */
@Configuration
public class ClientConfig {

    /**
     *
     * @return 服务的代理Bean
     */
    @Bean(name = "spitterService")
    public RmiProxyFactoryBean spitterService(){
        RmiProxyFactoryBean proxyFactoryBean=new RmiProxyFactoryBean();
        proxyFactoryBean.setServiceUrl("rmi://localhost:1099/SpitterService");
        proxyFactoryBean.setServiceInterface(SpitterService.class);
        return proxyFactoryBean;
    }

}
