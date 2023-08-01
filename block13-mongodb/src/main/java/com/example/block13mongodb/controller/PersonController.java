package com.example.block13mongodb.controller;

import com.example.block13mongodb.application.PersonService;
import com.example.block13mongodb.controller.dto.PersonInputDto;
import com.example.block13mongodb.controller.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    PersonService personService;

    @PostMapping
    public ResponseEntity<PersonOutputDto> addPerson(@RequestBody PersonInputDto person) {
        URI location = URI.create("/person");
        return ResponseEntity.created(location).body(personService.addPerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonOutputDto> getPersonById(@PathVariable String id) {
        PersonOutputDto person = personService.getPersonId(id);
        return ResponseEntity.ok().body(person);
    }

    @GetMapping
    public Iterable<PersonOutputDto> getAllPersons(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "10", required = false) int pageSize) {
        return personService.getAllPersons(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDto> updatePerson(@RequestBody PersonInputDto person, @PathVariable String id) {
        personService.getPersonId(id);
        return ResponseEntity.ok().body(personService.updatePerson(person, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson(@PathVariable String id) {
        personService.getPersonId(id);
        personService.deletePerson(id);
        return ResponseEntity.ok().body("Person with id: " + id + " has been deleted.");
    }
}
