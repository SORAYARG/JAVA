package com.example.block6personcontrollers;

import org.springframework.stereotype.Service;

@Service
public class PersonService {

    public Person createPerson(String nombre, String poblacion, int edad) {
        return new Person(nombre, poblacion, edad);
    }

    public Person getPerson() {
        return new Person("John Doe", "Cityville", 25);
    }
}
