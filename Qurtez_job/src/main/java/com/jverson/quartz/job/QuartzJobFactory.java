package com.jverson.quartz.job;

 import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class QuartzJobFactory implements Job {

	public void execute( JobExecutionContext jobExecutionContext) throws JobExecutionException {
    	ScheduleJob scheduleJob = (ScheduleJob)jobExecutionContext.getMergedJobDataMap().get("scheduleJob");
    	String jobName = scheduleJob.getJobName();
    	
    	// execute task inner quartz system
    	// spring bean can be @Autowired
        System.out.println(jobName + " **** I'm UP");
    	
    	// use rabbit MQ to asynchronously notify the task execution in business system
    	// sender.send(scheduleJob.getInterfaceName());
    	
    	// simulate time-consuming task
    	if (jobName.equals("job_name_4") || jobName.equals("addjob")) {
			try {
				Thread.sleep(1000*60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
    	
	}

}
