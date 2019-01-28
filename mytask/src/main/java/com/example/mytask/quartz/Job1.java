package com.example.mytask.quartz;

import org.quartz.*;

public class Job1 implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("Job 1");
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        System.out.println(jobDataMap.getString("jobSays"));
        System.out.println(jobDataMap.getFloat("myFloatValue"));
    }
}
