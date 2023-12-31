package com.example.block7crudvalidation.person.infraestructure.controller;

import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import com.example.block7crudvalidation.person.application.PersonServiceImpl;
import com.example.block7crudvalidation.person.auth.AuthenticationRequest;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;
    @CrossOrigin(origins = "https://cdpn.io")
    @PostMapping("/auth")

    public ResponseEntity<PersonOutputDto> addPerson (@RequestBody PersonInputDto person) throws UnprocessableEntityException {
        URI location = URI.create("/person");
        return ResponseEntity.created(location).body(personService.addPerson(person));
    }

    @GetMapping("/{id}")
    public ResponseEntity getPersonById(@PathVariable Integer id,
                                        @RequestParam(defaultValue = "simple", required = false)
                                        String outputType){
        PersonOutputDto person = personService.getPersonById(id);
        ResponseEntity response = ResponseEntity.ok().body(person);
        if(outputType.equals("simple")){
            response = ResponseEntity.ok().body(person);
        } else if (outputType.equals("full")){
            switch (personService.getTypeOfPerson(person.getPersonId())) {
                case "Student":
                    response = ResponseEntity.ok()
                            .body(personService.getPersonByIdStudent(id));
                    break;
                case "Teacher":
                    response = ResponseEntity.ok()
                            .body(personService.getPersonByIdTeacher(id));
            }
        } else {
            response = ResponseEntity.badRequest()
                    .body("El outputType debe ser 'simple' o 'full'");
        }
        return response;
    }

    @CrossOrigin(origins = "https://cdpn.io")
    @GetMapping
    public Iterable getAllPersons (@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                    @RequestParam(defaultValue = "4", required = false) int pageSize,
                                                    @RequestParam(defaultValue = "simple", required = false) String outputType) {
        Iterable person = null;
        if(outputType.equals("simple")){
            person = personService.getAllPersons(pageNumber, pageSize);
        } else if (outputType.equals("full")) {
            person = personService.getAllPersonFull(pageNumber, pageSize);
        }
        return person;
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonOutputDto> updatePerson (@RequestBody PersonInputDto person, @PathVariable Integer id){
        try {
            personService.getPersonById(id);
            return ResponseEntity.ok().body(personService.updatePerson(person, id));
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException();
        } catch (UnprocessableEntityException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePerson (@PathVariable Integer id){
        try {
            personService.getPersonById(id);
            personService.deletePerson(id);
            return ResponseEntity.ok().body("Delete person whit id: " + id);
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException();
        }
    }
    @PostMapping("/auth/login")
    public String login (@RequestBody AuthenticationRequest authenticationRequest){
        return personService.login(authenticationRequest.getUsername(), authenticationRequest.getPassword());
    }
}