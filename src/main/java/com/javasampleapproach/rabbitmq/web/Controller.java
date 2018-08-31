package com.javasampleapproach.rabbitmq.web;


import com.javasampleapproach.rabbitmq.model.ScheduleJob;
import com.javasampleapproach.rabbitmq.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    Producer producer;

    @RequestMapping("/job")
    public String senddMsg(@RequestParam("exchange")String exchange,@RequestParam("routingKey")String routingKey,
                           @RequestParam("id")Long id ,@RequestParam("name")String name,@RequestParam("groupe")String groupe
//                           ,@RequestParam("status")String status,@RequestParam("description")String description,
//                           @RequestParam("cronExpression")String cronExpression,
//                           @RequestParam("interfaceName")String interfaceName
    ){


        ScheduleJob job = new ScheduleJob();
        job.setJobId(id);
        job.setJobName(name);
        job.setJobGroup(groupe);



        job.setJobStatus("started");
        job.setDescription("i am job number test 4");
        job.setCronExpression("0/5 * * * * ?");
        job.setInterfaceName("test");

//        job.setJobStatus(status);
//        job.setDescription(description);
//        job.setCronExpression(cronExpression);
//        job.setInterfaceName(interfaceName);


        producer.producejob(exchange,routingKey,job);
        return "Done";
    }

}









