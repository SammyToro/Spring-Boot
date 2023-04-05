package com.apress.todo.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class ToDoProducer {
    
    private static final Logger log = LoggerFactory.getLogger(ToDoProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendTo(String destination, ToDo toDo){
        this.jmsTemplate.convertAndSend(destination, toDo);
        log.info("Producer > Message sent");
    }
}
