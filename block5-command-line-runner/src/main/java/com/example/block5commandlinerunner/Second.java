package com.example.block5commandlinerunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Second {
    @Bean
    public CommandLineRunner segundaFuncion() {
        return args -> {
            System.out.println("Hola desde clase secundaria");
        };
    }
}
