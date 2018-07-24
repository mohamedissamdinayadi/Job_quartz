package com.javasampleapproach.rabbitmq.producer;

import com.javasampleapproach.rabbitmq.model.Job;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Producer {
	
	@Autowired
	private AmqpTemplate amqpTemplate;
	
@Value("${jsa.rabbitmq.exchange}")
	private String exchange;
	
	@Value("${jsa.rabbitmq.routingkey}")
	private String routingkey;

	@Autowired
	private RabbitTemplate template;


	public void producejob(Job job){
		amqpTemplate.convertAndSend(exchange, routingkey, job);
		System.out.println("Send msg = " + job);
	}



	@RabbitListener(queues = "${jsa.rabbitmq.queue}" )
	public void recievedMessage(String s) {

		System.out.println(s);

	}




/*
 	public void producejob(Job job) {
		System.out.println(" [x] Requesting msg" + job + ")");
		Integer response = (Integer) template.convertSendAndReceive(exchange.getName(), "jsa.routingkey", start++);
		System.out.println();
		System.out.println(" [.] Got '" + response + "'");
	}

*/

}