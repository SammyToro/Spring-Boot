package com.apress.todo.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;

@Component
public class ToDoConsumer {

    private static final Logger log = LoggerFactory.getLogger(ToDoConsumer.class);
    private ToDoRepository repository;

    public ToDoConsumer(ToDoRepository repository){
        this.repository = repository;
    }

    public void handleMessage(ToDo toDo){
        log.info("Comsumer > " + toDo);
        log.info("ToDo created > " + this.repository.save(toDo));
    }
    
}
