package com.example.block6simplecontrollers;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @GetMapping("/user/{nombre}")
    public String saludar(@PathVariable String nombre) {
        return "Hola " + nombre;
    }

    @PostMapping("/useradd")
    public Person agregarUsuario(@RequestBody Person person) {
        int nuevaEdad = person.getEdad() + 1;
        person.setEdad(nuevaEdad);
        return person;
    }
}
