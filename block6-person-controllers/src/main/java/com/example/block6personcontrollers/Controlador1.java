package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    private final PersonService personService;

    @Autowired
    public Controlador1(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/addPersona")
    public Person addPersona(
            @RequestHeader String nombre,
            @RequestHeader String poblacion,
            @RequestHeader int edad
    ) {
        return personService.createPerson(nombre, poblacion, edad);
    }
}
