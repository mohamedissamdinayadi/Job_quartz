package com.javasampleapproach.rabbitmq;

import com.javasampleapproach.rabbitmq.model.Job;
import com.javasampleapproach.rabbitmq.model.ScheduleJob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javasampleapproach.rabbitmq.producer.Producer;

@SpringBootApplication
public class SpringRabbitMqProducerApplication  implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringRabbitMqProducerApplication.class, args);
	}
	
	@Autowired
	Producer producer;

	@Override
	public void run(String... args) throws Exception {
		/*
		 * Init Java objects
		 */

//		ScheduleJob job = new ScheduleJob();
//		job.setJobId("4");
//		job.setJobName("job_name_test4");
//		job.setJobGroup("job_group_test");
//		job.setJobStatus("started");
//		job.setDescription("i am job number test 4");
//		job.setCronExpression("0/5 * * * * ?");
//		job.setInterfaceName("test");
//
//		producer.producejob(job);
	}
}