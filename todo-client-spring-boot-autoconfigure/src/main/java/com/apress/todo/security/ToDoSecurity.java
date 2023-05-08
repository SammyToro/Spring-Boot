package com.apress.todo.security;

import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ToDoSecurity {

    private PasswordEncoder encoder;
}
