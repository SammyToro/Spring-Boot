package com.apress.todo;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;


@SpringBootTest
@AutoConfigureMockMvc
class TodoRestApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ToDoRepository toDoRepository;

	//MockMvc Test
	// @Test
	// public void toDoTest() throws Exception{
	// 	this.mockMvc
	// 		.perform(MockMvcRequestBuilders.get("/toDos"))
	// 		.andExpect(MockMvcResultMatchers.status().isOk())
	// 		.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
	// }

	@Test
	void contextLoads() {
	}

	//Mocking Bean Test
	@Test
	public void toDoTest2(){
		BDDMockito.given(this.toDoRepository.findById("my-id"))
		.willReturn(Optional.of(new ToDo("Read a Book")));
		Assertions.assertThat(this.toDoRepository.findById("my-id").get().getDescription())
		.isEqualTo("Read a Book");

	}

}
