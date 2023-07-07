package com.example.block7crudvalidation.person.infraestructure.repository;

import com.example.block7crudvalidation.person.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByName(String name);

}