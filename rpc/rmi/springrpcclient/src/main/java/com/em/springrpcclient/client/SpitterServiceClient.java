package com.em.springrpcclient.client;

import com.em.springrpcclient.services.SpitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Service;

/**
 * SpitterServiceClient class
 *
 * @author Administrator
 * @date
 */
@Service
public class SpitterServiceClient {

    @Autowired
    RmiProxyFactoryBean rmiService;

    public void getRencentSpitters() throws Exception {
        SpitterService spitterService=(SpitterService)rmiService.getObject();
        spitterService.getRencentSpitters(10);
    }

}
