package com.apress.todo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "todo.client")
public class ToDoClientProperties {

    private String host = "http://localhost:8080";
    private String path = "/toDos";

}
