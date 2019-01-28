package com.example.mytask.quartz;

import org.quartz.*;

public class Job1Listener implements JobListener {
    @Override
    public String getName() {
        return "Job1";
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        System.out.println("job1即将开始执行");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        System.out.println("job1被取消");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        System.out.println("job1执行结束");
    }
}