package com.example.mytask.quartz.inherit;

import org.quartz.JobKey;
import org.quartz.impl.JobDetailImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringConfig {

    @Bean
    public JobDetailImpl jobDetail(){
        JobDetailImpl jobDetail=new JobDetailImpl();
        jobDetail.setKey(new JobKey("MyJob"));
        return jobDetail;
    }

}
