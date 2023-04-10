package com.apress.todo.rmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apress.todo.domain.ToDo;

@Component
public class ToDoProducer {
    
    private static final Logger log = LoggerFactory.getLogger(ToDoProducer.class);

    @Autowired
    private RabbitTemplate template;

    public void sendTo(String queue, ToDo toDo){
        this.template.convertAndSend(queue, toDo);
        log.info("Producer > Message sent");
    }
}
