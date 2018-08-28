package com.em.mybatisgj.conf;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import  com.em.mybatisgj.interceptor.MyInterceptor;
import java.util.List;

/**
 * WebConfig class
 *
 * @author Administrator
 * @date
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    /**
     * 静态访问资源
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }


    /**
     * 配置拦截器
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**").excludePathPatterns("/toLogin","/login");
        super.addInterceptors(registry);
    }

    /**
     * 跨域
     */
    @Override
    protected void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
    }

    /**
     * 请求/toLogin直接访问login页面
     */
    @Override
    protected void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/").setViewName("index");
        registry.addViewController("/toLogin").setViewName("login");
        super.addViewControllers(registry);
    }

//    /**
//     * 视图解析器
//     */
//    @Override
//    protected void configureViewResolvers(ViewResolverRegistry registry) {
//        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
//        internalResourceViewResolver.setPrefix("/templates");
//        internalResourceViewResolver.setSuffix(".html");
//
//        registry.viewResolver(internalResourceViewResolver);
//        super.configureViewResolvers(registry);
//    }
//
//    /**
//     * 返回消息转换器
//     */
//    @Override
//    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        //创建fastJson消息转换器
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        //创建配置类
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        //修改配置返回内容的过滤
//        fastJsonConfig.setSerializerFeatures(
//                SerializerFeature.DisableCircularReferenceDetect,
//                SerializerFeature.WriteMapNullValue,
//                SerializerFeature.WriteNullStringAsEmpty
//        );
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        //将fastjson添加到视图消息转换器列表内
//        converters.add(fastConverter);
//
//        super.configureMessageConverters(converters);
//    }

}
