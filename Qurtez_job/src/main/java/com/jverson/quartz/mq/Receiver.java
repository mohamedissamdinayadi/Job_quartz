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

    @Value("${jsa.rabbitmq.routingkey.add}")
    private String routingkeyadd;

    @Value("${jsa.rabbitmq.routingkey.pause}")
    private String routingkeypause;
    @Autowired
    private JobService jobService;
    @Autowired
    JobRepository jobRepository;

    final private ScheduleJobService scheduleJobService;

    @Autowired
    public Receiver(ScheduleJobService scheduleJobService) {
        this.scheduleJobService = scheduleJobService;

    }


    @RabbitListener(queues = "${jsa.rabbitmq.queue.add}", containerFactory = "jsaFactory")
    public void recievedMessageAdd(ScheduleJob job) {
        Boolean existe = false;
        //Configure Job (Name, group, scheduler()

        //Create job: how ?

        try {
            //add job to scheduler and start it
            List<ScheduleJob> list = jobRepository.getAllByJobStatus();
            for (ScheduleJob s : list
                    ) {
                if (s.getJobName().equals(job.getJobName()) && s.getJobGroup().equals(job.getJobGroup())) {
                    existe = true;
                }

            }
            if ((!existe)) {
                scheduleJobService.addJob(job);
                //save job in data base
                jobService.save(job);

                amqpTemplate.convertAndSend(exchange, routingkey, "job created");

            }
            else {
                amqpTemplate.convertAndSend(exchange, routingkey, "you can't");
          }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


  @RabbitListener(queues = "${jsa.rabbitmq.queue.pause}", containerFactory = "jsaFactory")
    public void recievedMessagePause(ScheduleJob job) {

        try {
            //pause job into scheduler
            scheduleJobService.pauseJob(job);
            //update job status in data base
            jobService.updatestatus(job);

        } catch (Exception e) {
            e.printStackTrace();
        }

        amqpTemplate.convertAndSend(exchange, routingkey, "job paused");


    }

/*
    @RabbitListener(queues = "${jsa.rabbitmq.queue}", containerFactory = "jsaFactory")
    public void recievedMessagedelete(ScheduleJob job) {

        try {
            //pause job into scheduler
            scheduleJobService.deleteJob(job);
            //update job status in data base
            jobService.deleteJob(job.getJobId());

        } catch (Exception e) {
            e.printStackTrace();
        }

        amqpTemplate.convertAndSend(exchange, routingkey, "job paused");


    }*/
}





