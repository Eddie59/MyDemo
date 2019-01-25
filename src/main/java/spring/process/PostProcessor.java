package spring.process;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;


public class PostProcessor implements BeanPostProcessor {
    /**
     * 在bean实例化，依赖注入之后及自定义初始化方法(例如：配置文件中bean标签添加init-method属性指定Java类中初始化方法、
     * @PostConstruct注解指定初始化方法，Java类实现InitailztingBean接口)之前调用
     */
    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器处理bean=【"+beanName+"】开始");
        return bean;
    }


    /**
     * 在bean实例化、依赖注入及自定义初始化方法之后调用
     */
    @Nullable
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("后置处理器处理bean=【"+beanName+"】完毕!");
        return bean;
    }
}
