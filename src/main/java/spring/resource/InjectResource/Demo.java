package spring.resource.InjectResource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.InputStream;

/**
 * Demo class
 *
 * @author Administrator
 * @date
 */
public class Demo {
    @Test
    public void run() throws Exception
    {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring/resource/resourceInject.xml");
        ResourceBean3 resourceBean1 = ctx.getBean("resourceBean1", ResourceBean3.class);
        Assert.assertTrue(resourceBean1.getResource() instanceof ClassPathResource);
        Resource resource=resourceBean1.getResource();
        if(resource.exists())
        {
           InputStream stream= resource.getInputStream();
            byte[] bytes=new byte[stream.available()];
            stream.read(bytes);
            System.out.println(new String(bytes));
        }
    }
}
