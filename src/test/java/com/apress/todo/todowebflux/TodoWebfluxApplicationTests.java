package com.apress.todo.todowebflux;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.apress.todo.controller.ToDoController;
import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;

import reactor.core.publisher.Flux;

@WebFluxTest(ToDoController.class)
class TodoWebfluxApplicationTests {

	@Autowired
	private WebTestClient  webClient;

	@MockBean
	private ToDoRepository toDoRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void testExample() throws Exception{
		BDDMockito.given(this.toDoRepository.findAll())
		.willReturn(Flux.fromIterable(Arrays.asList(new ToDo("Read a Book"), new ToDo("Buy a Milk"))));

		this.webClient.get().uri("/todo").accept(MediaType.APPLICATION_JSON)
		.exchange()
		.expectStatus().isOk()
		.expectBody(List.class);
	}

}
