package com.apress.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.apress.todo.domain.ToDo;
import com.apress.todo.repository.ToDoRepository;

@SpringBootApplication
public class TodoReactiveDataApplication implements CommandLineRunner {

	@Autowired
	private ToDoRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(TodoReactiveDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stubth
		repository.save(new ToDo("Do homework")).subscribe();
		repository.save(new ToDo("Workout in the mornings", true)).subscribe();
		repository.save(new ToDo("Make dinner tonight")).subscribe();
		repository.save(new ToDo("Clean the studio", true)).subscribe();

		repository.findAll().subscribe(System.out::println);
	}

}
