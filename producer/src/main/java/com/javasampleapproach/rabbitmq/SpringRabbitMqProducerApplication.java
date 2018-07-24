package com.javasampleapproach.rabbitmq;

import com.javasampleapproach.rabbitmq.model.Job;
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

		Job job = new Job();
		job.setJobId("6");
		job.setJobName("job_name_test");
		job.setJobGroup("job_group_test");
		job.setJobStatus("Normal");
		job.setDesc("i am job number test");
		job.setCronExpression("0/5 * * * * ?");
		job.setInterfaceName("test");

		producer.producejob(job);
	}
}