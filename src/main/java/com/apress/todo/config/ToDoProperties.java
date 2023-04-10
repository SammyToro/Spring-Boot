package com.apress.todo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "todo.amqp")
public class ToDoProperties {

    private String queue;
    
}
