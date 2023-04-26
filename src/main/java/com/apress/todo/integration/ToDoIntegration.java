package com.apress.todo.integration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.MessageChannels;

import com.apress.todo.domain.ToDo;

@ImportResource("META-INF/spring/integration/todo-context.xml")
@EnableIntegration
@Configuration
public class ToDoIntegration {

    // @Bean
    // public DirectChannel input(){
    //     return MessageChannels.direct().get();
    // }

    // @Bean
    // public IntegrationFlow simpleFlow(){
    //     return IntegrationFlow.from(input())
    //                             .filter(ToDo.class, ToDo::isCompleted)
    //                             .transform(ToDo.class, toDo -> toDo.getDescription().toUpperCase())
    //                             .handle(System.out::println)
    //                             .get();
    // }
    
}
