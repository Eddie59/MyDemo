package com.invoker.invokerserver.config;

import com.invoker.invokerserver.service.SpitterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import java.util.Properties;

/**
 * ServiceConfig class
 *
 * @author Administrator
 * @date
 */
@Configuration
public class ServiceConfig {

    @Bean(name = "httpExportedSpitterService")
    public HttpInvokerServiceExporter httpExportedSpitterService(SpitterService spitterService) {
        HttpInvokerServiceExporter exporter = new HttpInvokerServiceExporter();
        exporter.setService(spitterService);
        exporter.setServiceInterface(SpitterService.class);
        return exporter;
    }

    @Bean
    public HandlerMapping httpInvokerMapping(){
        SimpleUrlHandlerMapping mapper=new SimpleUrlHandlerMapping();
        Properties mappings=new Properties();
        mappings.setProperty("/spitter.service","httpExportedSpitterService");
        mapper.setMappings(mappings);
        return mapper;
    }

}
