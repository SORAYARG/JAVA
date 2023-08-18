package com.example.block7crudvalidation.person.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonTeacherOutputDto {

    private Integer personId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String companyEmail;
    private String personalEmail;
    private String city;
    private Boolean active;
    private Date createdDate;
    private String imageUrl;
    private Date terminationDate;
    private Integer idTeacher;
    private String comments;
    private String branch;
}
