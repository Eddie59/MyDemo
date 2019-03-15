package spring.quartzdemo.origin.job;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloQuartz2 implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDetail jobDetail=context.getJobDetail();
        String name = jobDetail.getJobDataMap().getString("name");
        System.out.println("job 2 say hello to " + name + " at " + new Date());
    }
}
