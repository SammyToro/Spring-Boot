package com.apress.todo.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import com.apress.todo.domain.ToDo;

@Configuration
public class ToDoConfig {
    
    @Bean
    public ApplicationRunner runner(MessageChannel input){
        return args -> {
            input.send(MessageBuilder.withPayload(new ToDo("buy milk today",true))
                                        .build());
        };
    }
}
