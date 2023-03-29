package com.apress.directory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.apress.directory.domain.Person;

public interface PersonRepository extends JpaRepository<Person,String>{
    
    public Person findByEmailIgnoreCase(@Param("email") String email);
}
