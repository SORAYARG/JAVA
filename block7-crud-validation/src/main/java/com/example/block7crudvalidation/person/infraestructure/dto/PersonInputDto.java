package com.example.block7crudvalidation.person.infraestructure.dto;
import lombok.*;

import java.util.Date;

@Getter
@Setter

public class PersonInputDto {
    private String username;
    private String password;
    private String name;
    private String surname;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private boolean active;
    private String imageUrl;
    private Date terminationDate;
    private Date createdDate;


}