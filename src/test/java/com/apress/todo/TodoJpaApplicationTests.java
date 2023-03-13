package com.apress.todo;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.apress.todo.controller.ToDoController;
import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;

@WebMvcTest(ToDoController.class)
class TodoJpaApplicationTests {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ToDoRepository toDoRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void toDoControllerTest() throws Exception{
		BDDMockito.given(this.toDoRepository.findById("my-id"))
		.willReturn(Optional.of(new ToDo("Do Homework",true)));

		this.mvc.perform(MockMvcRequestBuilders.get("/api/todo/my-id")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("{\"id\": \"my-id\",\"description\": \"Do Homework\""+
															", \"completed\": true}"));
	}

}
