
package spring.resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.*;

import java.io.InputStream;

/**
 * ResourceLoaderDemo class
 *
 * @author Administrator
 * @date
 */
public class ResourceLoaderDemo {

    @Test
    public void testResourceLoad() throws Exception {
        //验证返回的是ClassPathResource，读classpath资源
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("classpath:cn/classloader/a.txt");
        if (resource.exists()) {
            print(resource);
        }
        Assert.assertEquals(ClassPathResource.class,resource.getClass());


        //验证返回的是UrlResource,FileUrlResource是UrlResource的一个子类，读file资源
        Resource resource1=loader.getResource("file:E:/user.sql");
        if(resource1.exists())
        {
            print(resource1);
            Assert.assertEquals(FileUrlResource.class,resource1.getClass());
        }
        //验证返回的是UrlResource,读http资源
        Resource resource3= loader.getResource("https://www.baidu.com/");
        if(resource3.exists())
        {
            System.out.println(resource3.getClass());
            print(resource3);
            Assert.assertEquals(UrlResource.class,resource3.getClass());
        }
        //验证返回的是UrlResource,读ftp://资源
        //Resource resource4=loader.getResource("ftp://***");


        //没有前缀时 根据ApplicationContext具体实现类采用对应的类型的Resource
        Resource resource2=loader.getResource("cn/classloader/a.txt");
        if(resource2.exists())
        {
            print(resource2);
        }
        System.out.println(resource2.getClass());//class org.springframework.core.io.DefaultResourceLoader$ClassPathContextResource
        Assert.assertTrue(resource2 instanceof ClassPathResource);//true，是ClassPathResource的子集
        Assert.assertEquals(ClassPathResource.class,resource2.getClass());//false
    }


    /**
     * 所有ApplicationContext都实现了ResourceLoader，因此可以使用其来加载资源
     */
    @Test
    public void testApplicationContext()
    {
//        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/resource/classloaderspring-config.xml");
//        ApplicationContext context1=new FileSystemXmlApplicationContext("file:E:/spring-config.xml");
//        ApplicationContext context2=new WebApplicationContext("");
    }



    private void print(Resource resource) throws Exception {
        if (resource.exists()) {
            InputStream inputStream = resource.getInputStream();
            byte[] bytes = new byte[inputStream.available()];
            inputStream.read(bytes);
            System.out.println(new String(bytes));
        }
    }
}
