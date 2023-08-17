package com.example.block7crudvalidation.person.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonOutputDto {

    Integer personId;
    String username;
    String password;
    String firstName;
    String lastName;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    Date createdDate;
    String imageUrl;
    Date terminationDate;

}