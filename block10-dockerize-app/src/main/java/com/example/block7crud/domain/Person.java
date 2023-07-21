package com.example.block7crud.domain;

import com.example.block7crud.controller.dto.PersonInputDto;
import com.example.block7crud.controller.dto.PersonOutputDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Integer age;
    String town;


    public Person(PersonInputDto personInputDto) {

        this.name = personInputDto.getName();
        this.age = personInputDto.getAge();
        this.town = personInputDto.getTown();
    }
    public PersonOutputDto personToPersonOutputDto() {
        return new PersonOutputDto(
                Math.toIntExact(this.id),
                this.name,
                this.age,
                this.town
        );
    }


    @Override
    public String toString() {
        return  "{\n" +
                "id : " + id + ", " + "\n" +
                "name : " + name + "," + "\n" +
                "age : " + age + "," + "\n" +
                "population : " + town + "\n" +
                "}\n";
    }
}