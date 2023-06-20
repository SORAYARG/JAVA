package com.example.block5commandlinerunner;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class Initial {

    @PostConstruct
    public void primeraFuncion() {
        System.out.println("Hola desde clase inicial");
    }

}
