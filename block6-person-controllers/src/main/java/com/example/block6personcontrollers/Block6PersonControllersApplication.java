package com.example.block6personcontrollers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Block6PersonControllersApplication {

	public static void main(String[] args) {
		SpringApplication.run(Block6PersonControllersApplication.class, args);
	}

	@Bean
	public List<Ciudad> ciudades() {
		return new ArrayList<>();
	}

	@Bean("bean1")
	public Person personBean1() {
		return new Person("Bean1", "City1", 20);
	}

	@Bean("bean2")
	public Person PersonBean2() {
		return new Person("Bean2", "City2", 25);
	}

	@Bean("bean3")
	public Person personBean3() {
		return new Person("Bean3", "City3", 30);
	}
}