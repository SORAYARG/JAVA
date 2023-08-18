package com.example.block7crudvalidation.student.infraestructure.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentFullOutputDto {

    Integer idStudent;
    int numHoursWeek;
    String comments;
    String branch;
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
    Integer idTeacher;

}