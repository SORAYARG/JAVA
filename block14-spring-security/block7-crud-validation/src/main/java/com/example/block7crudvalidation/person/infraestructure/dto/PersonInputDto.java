package com.example.block7crudvalidation.person.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class PersonInputDto {

    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private String imageUrl;
    private Date terminationDate;
    private Date createdDate;
    private boolean admin = false;


}