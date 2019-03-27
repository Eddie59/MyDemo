package com.em.discoverboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.util.Properties;
import java.util.List;

/**
 * SpringFactoriesLoaderTest class
 *
 * @author Administrator
 * @date
 */
@SpringBootApplication
public class SpringFactoriesLoaderTest {

/*
    public static void main(String... args) throws Exception {


        //PropertiesLoaderUtils
        Properties properties = PropertiesLoaderUtils.loadAllProperties("META-INF/my.factories");
        System.out.println(properties);
        Properties properties1= PropertiesLoaderUtils.loadAllProperties("META-INF/my.factories",ClassLoader.getSystemClassLoader());
        System.out.println(properties1);
        Properties properties2= PropertiesLoaderUtils.loadAllProperties("resources.properties");
        System.out.println(properties2);

        //SpringFactoriesLoader
        List<String> list=  SpringFactoriesLoader.loadFactoryNames(org.springframework.context.ApplicationContextInitializer.class,ClassLoader.getSystemClassLoader());
        System.out.println(list);
        List<org.springframework.context.ApplicationContextInitializer> listFactories= SpringFactoriesLoader.loadFactories(org.springframework.context.ApplicationContextInitializer.class,ClassLoader.getSystemClassLoader());
        System.out.println(listFactories);



    }
*/

}
