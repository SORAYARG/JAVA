package com.example.block7crudvalidation.person.infraestructure.controller;

import com.example.block7crudvalidation.person.application.PersonServiceImpl;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonServiceImpl personService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PersonOutputDto addPerson(@Valid @RequestBody PersonInputDto personInputDTO) {
        return personService.addPerson(personInputDTO);
    }
    @GetMapping
    public Iterable<PersonOutputDto> getAllPeople() {
        return personService.getPersons();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(personService.getPersonById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/name/{name}")
    public Iterable<PersonOutputDto> getPersonName(@PathVariable String name) {

        return personService.getPersonName(name);
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable int id) {
        personService.deletePersonById(id);
    }
}