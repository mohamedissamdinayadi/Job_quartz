package com.jverson.quartz;


import com.jverson.quartz.Job_db.JobRepository;
import com.jverson.quartz.job.ScheduleJob;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication


public class QuartzApplication {

    public static void main(String[] args) {

        SpringApplication.run(QuartzApplication.class, args);

    }
}
