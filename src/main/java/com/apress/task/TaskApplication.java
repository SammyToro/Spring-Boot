package com.apress.task;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.apress.todo.client.ToDoClient;
import com.apress.todo.domain.ToDo;
import com.apress.todo.security.ToDoSecurity;

@SpringBootApplication
@ComponentScan({"com.apress.todo"})
public class TaskApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TaskApplication.class);
		app.setWebApplicationType(WebApplicationType.NONE);
		app.run(args);
	}

	@Bean
	public ApplicationRunner createToDos(ToDoClient client){
		return args -> {
			ToDo toDo = client.add(new ToDo("Read a Book"));
			ToDo review = client.findById(toDo.getId());
			System.out.println(review);
			System.out.println(client.findAll());
		};
	}

	@Bean
	public ApplicationRunner secure(ToDoSecurity utils){
		return args -> {
			String text = "This text will be encrypted";
			String hash = utils.getEncoder().encode(text);
			System.out.println(">>> ENCRYPT: "+ hash);
			System.out.println(">>> Verify: "+utils.getEncoder().matches(text, hash));
		};
	}

}
