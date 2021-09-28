package com.demo.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.demo.Config.MessagingConfig;
import com.demo.dto.OrderStatus;

@Component
public class User {

	@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus) {

		System.out.println("message recieved is " + orderStatus);

	}

}
