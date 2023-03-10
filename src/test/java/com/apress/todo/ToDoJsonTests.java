package com.apress.todo;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;

import com.apress.todo.domain.ToDo;

@JsonTest
public class ToDoJsonTests {

    @Autowired
    private JacksonTester<ToDo> json;

    @Test
    public void toDoSerializeTest() throws Exception{
        ToDo todo = new ToDo("Read a Book");

        Assertions.assertThat(this.json.write(todo)).isEqualToJson("todo.json");
        Assertions.assertThat(this.json.write(todo)).hasJsonPathStringValue("@.description");
        Assertions.assertThat(this.json.write(todo)).extractingJsonPathStringValue("@.description")
        .isEqualTo("Read a Book");
    }

    @Test
    public void toDoDeserializeTest() throws Exception{
        String content = "{\"description\": \"Read a Book\",\"completed\": true}";
        Assertions.assertThat(this.json.parse(content))
        .isEqualTo(new ToDo("Read a Book",true));
        Assertions.assertThat(this.json.parseObject(content).getDescription()).isEqualTo("Read a Book");
    }
    
}
