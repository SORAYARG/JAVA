package com.example.block7crudvalidation.person.domain;

import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "Persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String username;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;

    public void update(PersonInputDto personInputDto) {
        if (personInputDto.getUsername() != null && personInputDto.getUsername().length() <= 10 && personInputDto.getUsername().length() >= 6) {
            setUsername(personInputDto.getUsername());
        }
        if (personInputDto.getName() != null) {
            setName(personInputDto.getName());
        }
        if (personInputDto.getSurname() != null) {
            setSurname(personInputDto.getSurname());
        }
        if (personInputDto.getPassword() != null) {
            setPassword(personInputDto.getPassword());
        }
        if (personInputDto.getCompanyEmail() != null) {
            setCompanyEmail(personInputDto.getCompanyEmail());
        }
        if (personInputDto.getPersonalEmail() != null) {
            setPersonalEmail(personInputDto.getPersonalEmail());
        }
        if (personInputDto.getCity() != null) {
            setCity(personInputDto.getCity());
        }
        if (personInputDto.getImageUrl() != null) {
            setImageUrl(personInputDto.getImageUrl());
        }
}

    public Person(PersonInputDto personInputDTO) {
        this.username = personInputDTO.getUsername();
        this.password = personInputDTO.getPassword();
        this.name = personInputDTO.getName();
        this.surname = personInputDTO.getSurname();
        this.companyEmail = personInputDTO.getCompanyEmail();
        this.personalEmail = personInputDTO.getPersonalEmail();
        this.city = personInputDTO.getCity();
        this.active = personInputDTO.isActive();
        this.imageUrl = personInputDTO.getImageUrl();
        this.terminationDate = personInputDTO.getTerminationDate();
        this.createdDate = personInputDTO.getCreatedDate();
        }
}