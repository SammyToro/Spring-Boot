package com.apress.todo.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.apress.todo.config.ToDoProperties;
import com.apress.todo.domain.ToDo;

@Component
@RepositoryEventHandler(ToDo.class)
public class ToDoEventHandler {

    private Logger log = LoggerFactory.getLogger(ToDoEventHandler.class);
    private SimpMessagingTemplate simpMessagingTemplate;
    private ToDoProperties toDoProperties;

    public ToDoEventHandler(SimpMessagingTemplate simpMessagingTemplate,ToDoProperties toDoProperties){
        this.toDoProperties = toDoProperties;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @HandleAfterCreate
    public void handleToDoSave(ToDo toDo){
        this.simpMessagingTemplate.convertAndSend(this.toDoProperties.getBroker() + "/new",toDo);
        log.info(">> Sending Message to WS: ws://todo/new - " + toDo);
    }
    
}
