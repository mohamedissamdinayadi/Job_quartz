package com.jverson.quartz.Job_db;


import com.jverson.quartz.job.ScheduleJob;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<ScheduleJob, Long> {

    @Query("select J from ScheduleJob J WHERE J.jobStatus='started'")
    List<ScheduleJob> getAllByJobStatus();

    @Query("select J from ScheduleJob J WHERE J.jobName= :jobName")
    ScheduleJob getJobByName(@Param("jobName") String jobName);
}
