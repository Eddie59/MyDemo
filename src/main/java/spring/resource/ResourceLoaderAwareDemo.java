package spring.resource;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ResourceLoader;

/**
 * ResourceLoaderAwareDemo class
 *
 * @author Administrator
 * @date
 */
public class ResourceLoaderAwareDemo implements ResourceLoaderAware {
    private ResourceLoader loader;

    public ResourceLoader getResourceLoader() {
        return loader;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.loader = resourceLoader;
    }

    @Test
    public void run()
    {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:spring/resource/spring-config.xml");
        ResourceLoaderAwareDemo resourceLoaderAware= context.getBean(ResourceLoaderAwareDemo.class);

        ResourceLoader resourceLoader= resourceLoaderAware.getResourceLoader();
        Assert.assertTrue(resourceLoader instanceof ApplicationContext);
    }
}
