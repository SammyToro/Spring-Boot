package com.apress.todo.cloud;

import java.util.function.Supplier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import com.apress.todo.domain.ToDo;

@Configuration
public class ToDoSource {

    @Bean
    public Supplier<Message<ToDo>> simpleToDo(){
        return () -> MessageBuilder
                        .withPayload(new ToDo("Test Spring Cloud Stream"))
                        .build();
    }
}
