package com.javasampleapproach.rabbitmq.producer;


import com.javasampleapproach.rabbitmq.model.ScheduleJob;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Value("${jsa.rabbitmq.exchange}")
    private String exchange;

    @Value("${jsa.rabbitmq.routingkey}")
    private String routingkey;

//    public void producejob(ScheduleJob job) {
//        amqpTemplate.convertAndSend(exchange, routingkey, job);
//        System.out.println("Send msg = " + job);
//    }

    public void producejob(String exchange, String routingKey, ScheduleJob job) {
        amqpTemplate.convertAndSend(exchange, routingKey, job);
        System.out.println("Send msg = " + job);
    }

//    @Bean
//    public CachingConnectionFactory rabbitConnectionFactory(RabbitProperties config)
//            throws Exception {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.getRabbitConnectionFactory().setUri("amqps://admin:admin@localhost:5672/virtualhost");
//        return connectionFactory;
//    }

    @RabbitListener(queues = "${jsa.rabbitmq.queue}")
    public void recievedMessage(String s) {

        System.out.println(s);

    }

}