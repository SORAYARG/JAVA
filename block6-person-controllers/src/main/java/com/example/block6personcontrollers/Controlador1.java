package com.example.block6personcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/controlador1")
public class Controlador1 {

    private final PersonService personService;
    private final CiudadService ciudadService;

    @Autowired
    public Controlador1(PersonService personService, CiudadService ciudadService) {

        this.personService = personService;
        this.ciudadService = ciudadService;
    }

    @PostMapping("/addPersona")
    @ResponseBody
    public Person addPersona(
            @RequestHeader String nombre,
            @RequestHeader String poblacion,
            @RequestHeader int edad
    ) {
        return personService.createPerson(nombre, poblacion, edad);
    }
    @PostMapping("/addCiudad")
    @ResponseBody
    public void addCiudad(@RequestBody Ciudad ciudad) {
        ciudadService.addCiudad(ciudad);
    }
}
