package com.apress.todo.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.apress.todo.domain.ToDo;

@Component
public class ToDoMessageHandler {
    private Logger log  = LoggerFactory.getLogger(ToDoMessageHandler.class);

    public void process(ToDo todo){
        log.info(">>> {}", todo);
    }
}
