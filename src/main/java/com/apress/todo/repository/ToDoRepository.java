package com.apress.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.apress.todo.domain.ToDo;

public interface ToDoRepository extends CrudRepository<ToDo,String>  {
    
    public Iterable<ToDo> findByDescriptionContains(String description);
    
}
