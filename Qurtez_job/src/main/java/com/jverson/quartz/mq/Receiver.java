package com.jverson.quartz.mq;

import com.jverson.quartz.job.QuartzJobFactory;
import com.jverson.quartz.job.ScheduleJob;
import com.jverson.quartz.service.ScheduleJobService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class Receiver {

    private static Logger logger = LoggerFactory.getLogger(Receiver.class.getName());

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingkey;


    final  private ScheduleJobService scheduleJobService;

    @Autowired
    public Receiver(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;

    }



    QuartzJobFactory quartzJobFactory;

    @RabbitListener(queues = "${jsa.rabbitmq.queue}", containerFactory = "jsaFactory")
    public void recievedMessage(ScheduleJob job) {


        //Configure Job (Name, group, scheduler()

        //Create job: how ?

        try {
            scheduleJobService.addJob(job);

        } catch (Exception e) {
            e.printStackTrace();


        }



        amqpTemplate.convertAndSend(exchange, routingkey,"job created");


    }
}





