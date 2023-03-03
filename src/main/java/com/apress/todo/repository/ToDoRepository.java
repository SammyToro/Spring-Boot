package com.apress.todo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.apress.todo.domain.ToDo;

@Repository
public interface ToDoRepository extends ReactiveMongoRepository<ToDo,String>{
    
}
