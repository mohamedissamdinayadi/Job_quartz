package com.javasampleapproach.rabbitmq.dao;

import com.javasampleapproach.rabbitmq.model.ScheduleJob;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<ScheduleJob,Long> {
}


