package com.jverson.quartz.mq;

import com.jverson.quartz.Job_db.JobRepository;
import com.jverson.quartz.Job_db.JobService;
import com.jverson.quartz.job.ScheduleJob;
import com.jverson.quartz.service.ScheduleJobService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class Receiver {

    private static Logger logger = LoggerFactory.getLogger(Receiver.class.getName());

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingkey;

    //    @Value("${jsa.rabbitmq.routingkey.add}")
//    private String routingkeyadd;
//
//    @Value("${jsa.rabbitmq.routingkey.pause}")
//    private String routingkeypause;
    @Autowired
    private JobService jobService;
    @Autowired
    JobRepository jobRepository;

    final private ScheduleJobService scheduleJobService;

    @Autowired
    public Receiver(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;

    }


    private boolean existe(ScheduleJob job) {
        Boolean existe = false;
        List<ScheduleJob> list = jobRepository.findAll();
        for (ScheduleJob s : list
                ) {
            if (s.getJobName().equals(job.getJobName()) && s.getJobGroup().equals(job.getJobGroup())) {
                existe = true;
            } else {
                existe = false;
            }

        }
        return existe;
    }

    @RabbitListener(queues = "${jsa.rabbitmq.queue.add}", containerFactory = "jsaFactory")
    public void recievedMessageAdd(ScheduleJob job) {
        //Configure Job (Name, group, scheduler()
        //Create job: how ?
        try {

            if ((!existe(job))) {
                //add job to scheduler and start it
                scheduleJobService.addJob(job);
                //save job in data base
                jobService.save(job);

                amqpTemplate.convertAndSend(exchange, routingkey, "job created");

            } else {
                amqpTemplate.convertAndSend(exchange, routingkey, "job already exists");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @RabbitListener(queues = "${jsa.rabbitmq.queue.pause}", containerFactory = "jsaFactory")
    public void recievedMessagePause(ScheduleJob job) {

        try {
            if ((existe(job))) {
                //pause job into scheduler
                scheduleJobService.pauseJob(job);
                //update job status in data base
                jobService.updatestatuspause(job);
                amqpTemplate.convertAndSend(exchange, routingkey, "job paused");
            } else {
                amqpTemplate.convertAndSend(exchange, routingkey, "job not exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    @RabbitListener(queues = "${jsa.rabbitmq.queue.resume}", containerFactory = "jsaFactory")
    public void recievedMessageresume(ScheduleJob job) {
        try {
            if ((existe(job))) {
                //resume job into scheduler
                scheduleJobService.resumeJob(job);
                //update job status in data base
                jobService.updatestatusstart(job);
                amqpTemplate.convertAndSend(exchange, routingkey, "job resume");
            } else {
                amqpTemplate.convertAndSend(exchange, routingkey, "job not exists");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}





