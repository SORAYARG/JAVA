package com.example.block5commandlinerunner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class Third {
    @Bean
    public CommandLineRunner terceraFuncion() {
        return args -> {
            System.out.println("Soy la tercera clase");
            for (String arg : args) {
                System.out.println("Valor pasado como parámetro: " + arg);
            }
        };
    }
}
