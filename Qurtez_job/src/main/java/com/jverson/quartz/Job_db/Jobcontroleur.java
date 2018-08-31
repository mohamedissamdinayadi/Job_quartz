package com.jverson.quartz.Job_db;


import com.jverson.quartz.job.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Jobcontroleur {

    @Autowired
    private JobRepository jobRepository;




    @GetMapping("/jobs")

    public List<ScheduleJob> getJobs() {
        return jobRepository.findAll();
    }


}
