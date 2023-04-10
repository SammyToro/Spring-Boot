package com.apress.todo.rmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;

@Component
public class ToDoConsumer {

    private static Logger log = LoggerFactory.getLogger(ToDoConsumer.class);

    private ToDoRepository repository;

    public ToDoConsumer(ToDoRepository repository){
        this.repository = repository;
    }

    @RabbitListener(queues = "${todo.amqp.queue}")
    public void processToDo(ToDo toDo){
        log.info("Consumer > " + toDo);
        log.info("ToDo created > " + this.repository.save(toDo));
        
    }
    
}
