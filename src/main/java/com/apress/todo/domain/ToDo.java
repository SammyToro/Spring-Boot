package com.apress.todo.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class ToDo {

    @Id
    private String id;
    private String description;
    private LocalDateTime created;
    private LocalDateTime modified;
    private boolean completed;
    
    public ToDo(){
        this.id = UUID.randomUUID().toString();
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    public ToDo(String description){
        this();
        this.description = description;
    }

    public ToDo(String description,boolean completed){
        this(description);
        this.completed = completed;
    }
    
}
