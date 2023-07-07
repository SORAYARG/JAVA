package com.example.block7crudvalidation.person.infraestructure.dto;

import com.example.block7crudvalidation.person.domain.Person;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {
    private Integer Person;
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

    public PersonOutputDto(Person person)  {
        this.Person = person.getId();
        this.username = person.getUsername();
        this.password = person.getPassword();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.companyEmail = person.getCompanyEmail();
        this.personalEmail = person.getPersonalEmail();
        this.city = person.getCity();
        this.active = person.isActive();
        this.createdDate = person.getCreatedDate();
        this.imageUrl = person.getImageUrl();
        this.terminationDate = person.getTerminationDate();
    }
}