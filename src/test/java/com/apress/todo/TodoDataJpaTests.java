package com.apress.todo;

import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;

@DataJpaTest
public class TodoDataJpaTests {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired ToDoRepository repository;

    @Test
    public void toDoDataTest() throws Exception {
        this.entityManager.persist(new ToDo("Read a Book"));
        Iterable<ToDo> toDo = this.repository.findByDescriptionContains("Read a Book");
        AssertionsForClassTypes.assertThat(toDo.iterator().next()).toString().contains("Read a Book");
    }
    
}
