package com.apress.todo.cloud;

import java.time.LocalDateTime;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.todo.domain.ToDo;

@Configuration
public class ToDoProcessor {
    
    private Logger log = LoggerFactory.getLogger(ToDoProcessor.class);

    @Bean
    public Consumer<ToDo> transformToUpperCase(){
        return message -> {
            log.info("Processing >>> {}", message);
            ToDo result = message;
            result.setDescription(message.getDescription().toUpperCase());
            result.setCompleted(true);
            result.setModified(LocalDateTime.now());
            log.info("Message Processed >>> {}", result);
        };
    }
}
