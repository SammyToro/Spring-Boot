package com.apress.todo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.apress.todo.client.ToDoRestClient;
import com.apress.todo.client.domain.ToDo;

@SpringBootApplication
public class TodoClientApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TodoClientApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	private Logger logger = LoggerFactory.getLogger(TodoClientApplication.class);

	@Bean
	public CommandLineRunner process(ToDoRestClient client){
		return args -> {
			Iterable<ToDo> toDos = client.findAll();
			assert toDos != null;
			toDos.forEach(toDo -> logger.info(toDo.toString()));

			ToDo newToDo = client.insert(new ToDo("Drink plenty of water daily"));
			assert newToDo !=  null;
			logger.info(newToDo.toString());

			ToDo toDo = client.findById(newToDo.getId());
			assert toDo != null;
			logger.info(toDo.toString());

			ToDo completed = client.setCompleted(newToDo.getId());
			assert completed.isCompleted();
			logger.info(completed.toString());

			client.delete(newToDo.getId());
			assert client.findById(newToDo.getId()) == null;
		};
	}

}
