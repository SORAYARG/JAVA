package com.example.block6personcontrollers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    private final PersonService personService;
    private final CiudadService ciudadService;

    @Autowired
    public Controlador2(PersonService personService, CiudadService ciudadService) {

        this.personService = personService;
        this.ciudadService = ciudadService;
    }

    @GetMapping("/getPersona")
    @ResponseBody
    public Person getPersona() {
        Person person = personService.getPerson();
        person.setEdad(person.getEdad() * 2);
        return person;
    }

    @GetMapping("/getCiudades")
    @ResponseBody
    public List<Ciudad> getCiudades() {
        return ciudadService.getCiudades();
    }
}
