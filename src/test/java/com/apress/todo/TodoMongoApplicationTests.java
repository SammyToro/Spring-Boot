package com.apress.todo;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.apress.todo.domain.ToDo;

import de.flapdoodle.embed.mongo.spring.autoconfigure.EmbeddedMongoAutoConfiguration;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@ExtendWith(SpringExtension.class)
class TodoMongoApplicationTests {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Test
	void contextLoads() {
	}

	@Test
	public void toDoMongoTest(){
		ToDo todo = new ToDo("Read a Book");
		this.mongoTemplate.save(todo);

		ToDo result = this.mongoTemplate.findById(todo.getId(), ToDo.class);
		Assertions.assertThat(result.getId()).isEqualTo(todo.getId());
	}



}
