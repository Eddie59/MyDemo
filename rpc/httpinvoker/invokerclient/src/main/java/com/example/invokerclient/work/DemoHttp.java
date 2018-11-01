package com.example.invokerclient.work;

import com.example.invokerclient.service.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;
import org.springframework.stereotype.Service;

/**
 * DemoHttp class
 *
 * @author Administrator
 * @date
 */
@Service
public class DemoHttp {

    @Autowired
    private HttpInvokerProxyFactoryBean proxy;

    public void getRencentSpitters() throws Exception {
        SpitterService spitterService=(SpitterService)proxy.getObject();
        spitterService.getRencentSpitters(111);
    }

}
