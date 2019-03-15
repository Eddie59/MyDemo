package spring.quartzdemo.origin;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import spring.quartzdemo.origin.job.HelloQuartz1;

import static org.quartz.JobBuilder.newJob;

public class QuartzTest1 {
    public static void main(String[] args) throws Exception{
//        一个Quartz的独立运行容器
        Scheduler scheduler= StdSchedulerFactory.getDefaultScheduler();

        //定义触发器
        Trigger trigger= TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")//定义name/group，name为在scheduler中的唯一标识，group1给trigger编组，提供了对组操作的方法
                .startNow()//一旦加入scheduler，立即生效
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(1)
                        .repeatForever())//使用SimpleTrigger
                .build();

        //JobDetail 定义的是任务数据，而真正的执行逻辑是在Job中，例子中是HelloQuartz。 为什么设计成JobDetail + Job，不直接使用Job？
        // 这是因为任务是有可能并发执行，如果Scheduler直接使用Job，就会存在对同一个Job实例并发访问的问题。
        // 而JobDetail & Job 方式，sheduler每次执行，都会根据JobDetail创建一个新的Job实例，这样就可以规避并发访问的问题。
        JobDetail jobDetail=newJob(HelloQuartz1.class)//每次调度，都会new一个HelloQuartz的实例，方便并发
                .withIdentity("job1", "group1") //定义name/group，name为在scheduler中的唯一标识，group1给JobDetail编组，提供了对组操作的方法
                .usingJobData("name", "eddie") //定义数据
                .build();

        scheduler.scheduleJob(jobDetail,trigger);

        scheduler.start();

        //运行一段时间后关闭
        Thread.sleep(10000);
        scheduler.shutdown(true);
    }
}
