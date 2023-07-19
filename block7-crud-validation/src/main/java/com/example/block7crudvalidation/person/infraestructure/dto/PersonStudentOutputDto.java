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
public class PersonStudentOutputDto {

    private int idPerson;
    String username;
    String password;
    String name;
    String surname;
    String companyEmail;
    String personalEmail;
    String city;
    Boolean active;
    Date createdDate;
    String imageUrl;
    Date terminationDate;
    String idStudent;
    int numHoursWeek;
    String comments;
    String branch;
}
