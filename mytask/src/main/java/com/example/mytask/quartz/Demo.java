package com.example.mytask.quartz;

import org.junit.jupiter.api.Test;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;
import static org.quartz.impl.matchers.GroupMatcher.jobGroupEquals;

public class Demo {

    @Test
    public void run(){
        try{
            // define the job and tie it to our HelloJob class
            JobDetail job = newJob(Job1.class)
                    .withIdentity("myJob", "group1")
                    //设置jobdata
                    .usingJobData("jobSays", "Hello World!")
                    .usingJobData("myFloatValue", 3.141f)
                    .build();

            // Trigger the job to run now, and then every 40 seconds
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .startNow()
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(2)
                            .repeatForever())
                    .build();

            SchedulerFactory schedFact = new org.quartz.impl.StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            //添加TriggerListener
            Job1Listener job1Listener=new Job1Listener();
            sched.getListenerManager().addJobListener(job1Listener,jobGroupEquals("group1"));
            sched.start();

            // Tell quartz to schedule the job using our trigger
            sched.scheduleJob(job, trigger);

            Thread.sleep(10000);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
