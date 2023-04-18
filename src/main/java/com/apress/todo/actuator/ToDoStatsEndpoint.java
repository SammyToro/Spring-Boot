package com.apress.todo.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Component
@Endpoint(id = "todo-stats")
public class ToDoStatsEndpoint {

    private ToDoRepository toDoRepository;

    ToDoStatsEndpoint(ToDoRepository toDoRepository){
        this.toDoRepository = toDoRepository;
    }
    
    @ReadOperation
    public Stats stats(){
        return new Stats(this.toDoRepository.count(),this.toDoRepository.countByCompleted(true));
    }

    @ReadOperation
    public ToDo getToDo(@Selector String id){
        return this.toDoRepository.findById(id).orElse(null);
    }

    @WriteOperation
    public Operation completeToDo(@Selector String id){
        ToDo toDo = this.toDoRepository.findById(id).orElse(null);
        if(toDo != null){
            toDo.setCompleted(true);
            this.toDoRepository.save(toDo);
            return new Operation("COMPLETED", true);
        }

        return new Operation("COMPLETED", false);

    }

    @DeleteOperation
    public Operation removeToDo(@Selector String id){
        try{
            this.toDoRepository.deleteById(id);
            return new Operation("DELETED",true);
        }catch(Exception ex){
            return new Operation("DELETED",false);
        }
    }

    @AllArgsConstructor
    @Data
    public class Stats {
        private long count;
        private long completed;
    }

    @AllArgsConstructor
    @Data
    public class Operation{
        private String name;
        private boolean successful;
    }
}
