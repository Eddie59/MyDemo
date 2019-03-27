package spring.environment;

import org.junit.Test;
import org.springframework.core.env.*;
import org.springframework.core.io.support.ResourcePropertySource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * PropertySourceDemo class
 *
 * @author Administrator
 * @date
 */
public class PropertySourceDemo {

    public static void main(String... args) {
        //getenv方法返回的变量大多于系统相关
        //getProperty方法返回的变量大多与java程序有关
        Properties properties = System.getProperties();
        Map<String, String> map = System.getenv();

        //getenv getProperty获取的系统变量会保存到Environment中
        Environment environment = new StandardEnvironment();
        //打印
        String s1 = environment.getProperty("file.encoding");
        String s2 = environment.getProperty("java.specification.version");
        String s3 = environment.getProperty("LOCALAPPDATA");


    }


    @Test
    public void Run() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("encoding", "gbk");

        PropertySource propertySource1 = new MapPropertySource("MapPropertySource", map);
        System.out.println(propertySource1.getName());
        System.out.println(propertySource1.getProperty("encoding"));
        Map map1 = (Map) propertySource1.getSource();

        //name location
        PropertySource propertySource2 = new ResourcePropertySource("ResourcePropertySource", "classpath:/spring/environment/conf.properties");
        System.out.println(propertySource2.getProperty("a"));
        System.out.println(propertySource2.getName());
        Map source = (Map) propertySource2.getSource();
        for (Object key : source.keySet()) {
            System.out.println(key + ":" + source.get(key));
        }

        //省略propertySource1/propertySource2
        CompositePropertySource compositePropertySource = new CompositePropertySource("compositePropertySource");
        compositePropertySource.addPropertySource(propertySource1);
        compositePropertySource.addPropertySource(propertySource2);
        System.out.println(compositePropertySource.getProperty("encoding"));

        //省略propertySource1/propertySource2
        MutablePropertySources propertySources = new MutablePropertySources();
        propertySources.addFirst(propertySource1);
        propertySources.addLast(propertySource2);
        //根据Name获取PropertySource
        System.out.println(propertySources.get("MapPropertySource").getProperty("encoding"));
        for (PropertySource propertySource : propertySources) {
            System.out.println(propertySource.getProperty("encoding"));
        }


        PropertyResolver propertyResolver = new PropertySourcesPropertyResolver(propertySources);
        System.out.println(propertyResolver.getProperty("encoding"));
        System.out.println(propertyResolver.getProperty("no", "default"));//找不到no，使用默认值default
        System.out.println(propertyResolver.resolvePlaceholders("must be encoding ${encoding}"));  //输出must be encoding gbk


    }
}
