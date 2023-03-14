package com.apress.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.CommonRepository;
import com.apress.todo.repository.ToDoRepository;

@JdbcTest
class TodoJdbcApplicationTests {

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	private CommonRepository<ToDo> repository;

	@Test
	void contextLoads() {
	}

	@Test
	public void todoJdbcTest(){
		ToDo toDo = new ToDo("Read a Book");

		this.repository = new ToDoRepository(jdbcTemplate);
		this.repository.save(toDo);

		ToDo result = this.repository.findById(toDo.getId());
		Assertions.assertThat(result.getId()).isEqualTo(toDo.getId());
	}

}
