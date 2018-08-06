package com.jverson.quartz.Job_db;

import com.jverson.quartz.job.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    JobRepository jobRepository;


    public ScheduleJob save(ScheduleJob job) {
        return jobRepository.save(job);
    }

    public List<ScheduleJob> getJobs() {
        return jobRepository.findAll();
    }

    public ScheduleJob getJob(Long id) {
        return jobRepository.findOne(id);
    }

    public void deleteJob(Long id) {
        jobRepository.delete(id);
    }

    public void updatestatuspause(ScheduleJob job) {
        job.setJobStatus("paused");
        jobRepository.save(job);
    }

    public void updatestatusstart(ScheduleJob job) {
        job.setJobStatus("started");
        jobRepository.save(job);
    }
}
