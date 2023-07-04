package com.example.block7crud.controller;

import com.example.block7crud.application.PersonServiceImpl;
import com.example.block7crud.controller.dto.PersonInputDto;
import com.example.block7crud.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    PersonServiceImpl personService;


    @PostMapping("/addperson")
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) throws Exception {
        URI location = URI.create("/person");

        return ResponseEntity.created(location).body(personService.addPerson(person));
    }
    @GetMapping
    public Iterable<PersonOutputDto> getAllPeople() {
        return personService.getAllPerson();
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
    public Iterable<PersonOutputDto> getAllPeopleByName(@PathVariable String name) {

        return personService.getPeopleByName(name);
    }

    @DeleteMapping("/{id}")
    public void deletePersonById(@PathVariable int id) {
        personService.deletePersonById(id);
    }
}