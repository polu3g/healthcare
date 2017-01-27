package com.pc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import com.pc.entity.StudentEntity;
import com.pc.entity.Customer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.AmqpException;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@RestController
public class StudentsController {

	@Autowired
	private SQLSrvRepository sqlSrvRepository;

	@Autowired
	private CustomerRepository mongoRepository;

	@Autowired
	private AmqpAdmin admin;

	@Autowired
	private AmqpTemplate template;

	@Autowired
    SimpMessagingTemplate WStemplate;
    
	@RequestMapping("/findall")
	public List findall(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		List<StudentEntity> pupils = (ArrayList<StudentEntity>) sqlSrvRepository
				.findAll();
		return pupils;
	}

	@RequestMapping("/findallmongo")
	public List findallmongo(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		List<Customer> pupils = (ArrayList<Customer>) mongoRepository.findAll();
		return pupils;
	}

	@RequestMapping("/publishamqp")
	public String publishamqp(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		try {
			String sent = "[{ message : \"Catch the rabbit!\", date : \"" + new java.util.Date() + "\"}]";
			Queue queue = new Queue("healthcareQueue");
			admin.declareQueue(queue);
			TopicExchange exchange = new TopicExchange("healthcareExchange");
			admin.declareExchange(exchange);
			admin.declareBinding(BindingBuilder.bind(queue).to(exchange)
					.with("pc.*"));
			// write message
			template.convertAndSend("healthcareExchange", "pc.test", sent);
			return sent;

		} catch (AmqpException e) {
			return ("call failed: " + e.getLocalizedMessage());
		}
	}

	@RequestMapping("/getamqp")
	public String getamqp(
			@RequestParam(value = "name", defaultValue = "World") String name) {
		try {
			// read message
			String received = (String) template
					.receiveAndConvert("healthcareQueue");
			return received;

		} catch (AmqpException e) {
			return ("call failed: " + e.getLocalizedMessage());
		}
	}
	
	@Scheduled(fixedDelay = 20000L)
    @SendTo("/topic/pingpong")
    public void sendPong() {
		WStemplate.convertAndSend("/topic/pingpong", "pong (periodic) @ " + new java.util.Date() );
    }
 
    @MessageMapping("/ping")
    @SendTo("/topic/pingpong")
    public String sendPingResponse() {
        return "pong (response)";
    }	
}
