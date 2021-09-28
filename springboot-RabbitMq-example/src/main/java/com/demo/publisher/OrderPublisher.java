package com.demo.publisher;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Config.MessagingConfig;
import com.demo.dto.Order;
import com.demo.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {
	@Autowired
	private RabbitTemplate template;

	@PostMapping("/{restaurantName}")
	public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {

		order.setOrderId(UUID.randomUUID().toString());

		OrderStatus orderStatus = new OrderStatus(order, "Processing", "order places successfully in : " + restaurantName);

		template.convertAndSend(MessagingConfig.Exchange, MessagingConfig.ROUTING_KEY, orderStatus);

		return "SUCCESS !!";
	}

}
