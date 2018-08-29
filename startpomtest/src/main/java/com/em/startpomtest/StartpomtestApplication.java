package com.em.startpomtest;

import em.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartpomtestApplication {

    private static HelloService helloService;

    @Autowired
    private void setHelloService(HelloService _helloService) {
        helloService = _helloService;
    }

    public static void main(String[] args) {
        SpringApplication.run(StartpomtestApplication.class, args);

        String str=helloService.sayHello();
        System.out.println(str);
    }
}
