package com.example.block6personcontrollers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador2")
public class Controlador2 {

    private final PersonService personService;

    @Autowired
    public Controlador2(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/getPersona")
    public Person getPersona() {
        Person person = personService.getPerson();
        person.setEdad(person.getEdad() * 2);
        return person;
    }
}
