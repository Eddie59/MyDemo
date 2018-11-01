package com.em.springrpc.config;

import com.em.springrpc.services.SpitterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.rmi.RmiServiceExporter;

/**
 * MainConfig class
 *
 * @author Administrator
 * @date
 */
@Configuration
public class ServiceConfig {

    /**
     *
     * @param spitterService
     * @return 把SpitterService包装成RMI服务
     */
    @Bean
    public RmiServiceExporter getSpitterService(SpitterService spitterService){
        RmiServiceExporter exporter=new RmiServiceExporter();
        exporter.setService(spitterService);
        exporter.setServiceName("SpitterService");
        exporter.setServiceInterface(SpitterService.class);
        return exporter;
    }
}
