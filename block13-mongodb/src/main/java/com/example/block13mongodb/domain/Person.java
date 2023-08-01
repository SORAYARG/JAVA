package com.example.block13mongodb.domain;

import com.example.block13mongodb.controller.dto.PersonInputDto;
import com.example.block13mongodb.controller.dto.PersonOutputDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@Document(collection = "persons")
public class Person {

    @Id
    private String idPerson;
    private String username;
    private String password;
    private String name;
    private String firstname;

    public Person (PersonInputDto personInputDto){
        this.idPerson = personInputDto.getIdPerson();
        this.username = personInputDto.getUsername();
        this.password = personInputDto.getPassword();
        this.name = personInputDto.getName();
        this.firstname = personInputDto.getFirstname();
}
    public PersonOutputDto personToPersonOutputDto (){
        return new PersonOutputDto(
                this.idPerson,
                this.username,
                this.password,
                this.name,
                this.firstname
                );
    }
}
