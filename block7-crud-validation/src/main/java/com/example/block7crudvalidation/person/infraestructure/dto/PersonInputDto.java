package com.example.block7crudvalidation.person.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PersonInputDto {
    int id;
    String username;
    String password;
    String name;
    String lastname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    String imageUrl;
    Date terminationDate;
    Date createdDate;


}