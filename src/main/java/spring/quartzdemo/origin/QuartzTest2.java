package spring.quartzdemo.origin;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import spring.quartzdemo.origin.job.HelloQuartz1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.quartz.JobBuilder.newJob;

public class QuartzTest2 {
    public static void main(String...args) throws Exception{
        Trigger trigger1= TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")//定义name/group，name为在scheduler中的唯一标识，group1给trigger编组，提供了对组操作的方法
                .startNow()//一旦加入scheduler，立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())//使用SimpleTrigger
                .build();
        Trigger trigger2= TriggerBuilder.newTrigger()
                .withIdentity("trigger2", "group1")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(2)
                        .repeatForever())
                .build();


        JobDetail jobDetail=newJob(HelloQuartz1.class)//每次调度，都会new一个HelloQuartz的实例，方便并发
                .withIdentity("job1", "group1")
                .withDescription("这是对job1的描述")
                .usingJobData("name", "eddie")
                .build();

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //一个job对应多个trigger,但是一个trigger只能对应一个job
        Map<JobDetail, Set<? extends Trigger>> map=new HashMap<>();
        HashSet<Trigger> triggerSet = new HashSet<>();
        triggerSet.add(trigger1);
        triggerSet.add(trigger2);
        map.put(jobDetail,triggerSet);
        scheduler.scheduleJobs(map,true);

        scheduler.start();


    }
}
