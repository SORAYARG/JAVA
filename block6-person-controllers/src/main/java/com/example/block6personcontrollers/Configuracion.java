package com.example.block6personcontrollers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuracion {
    @Bean
    public Person bean1() {
        return new Person("Bean 1", "Ciudad Bean 1", 25);
    }

    @Bean
    public Person bean2() {
        return new Person("Bean 2", "Ciudad Bean 2", 30);
    }

    @Bean
    public Person bean3() {
        return new Person("Bean 3", "Ciudad Bean 3", 35);
    }
}
