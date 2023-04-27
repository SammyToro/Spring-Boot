package com.apress.todo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "todo")
public class ToDoProperties {
    private String directory;
    private String filePattern;
}
