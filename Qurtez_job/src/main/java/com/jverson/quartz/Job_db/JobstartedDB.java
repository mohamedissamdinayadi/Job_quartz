package com.jverson.quartz.Job_db;

import com.jverson.quartz.job.ScheduleJob;
import com.jverson.quartz.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JobstartedDB {

    @Autowired
    JobRepository jobRepository;

    final private ScheduleJobService scheduleJobService;


    @Autowired
    public JobstartedDB(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;
    }

    @Autowired
    public void started() {
        List<ScheduleJob> scheduleJobList = jobRepository.getAllByJobStatus();
        for (ScheduleJob s : scheduleJobList) {

            try {
                scheduleJobService.addJob(s);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



}
