package com.example.block7crudvalidation.person.domain;

import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue
    int personId;
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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Student student;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    Teacher teacher;

    public Person(PersonInputDto personInputDto) throws UnprocessableEntityException {

        if (personInputDto.getUsername() == null) {
            throw new UnprocessableEntityException("Username cannot be null");
        } else if (personInputDto.getUsername().length() > 10) {
            throw new UnprocessableEntityException("Username length cannot exceed 10 characters");
        } else if (personInputDto.getUsername().length() < 6) {
            throw new UnprocessableEntityException("Username length cannot be less than 6 characters");
        } else {
            this.username = personInputDto.getUsername();
        }

        if (personInputDto.getPassword() == null) {
            throw new UnprocessableEntityException("Password cannot be null");
        } else {
            this.password = personInputDto.getPassword();
        }

        if (personInputDto.getName() == null) {
            throw new UnprocessableEntityException("First name cannot be null");
        } else {
            this.firstName = personInputDto.getName();
        }

        if (personInputDto.getCompanyEmail() == null) {
            throw new UnprocessableEntityException("Company email cannot be null");
        } else {
            this.companyEmail = personInputDto.getCompanyEmail();
        }

        if (personInputDto.getPersonalEmail() == null) {
            throw new UnprocessableEntityException("Personal email cannot be null");
        } else {
            this.personalEmail = personInputDto.getPersonalEmail();
        }

        if (personInputDto.getCity() == null) {
            throw new UnprocessableEntityException("City cannot be null");
        } else {
            this.city = personInputDto.getCity();
        }

        if (personInputDto.getActive() == null) {
            this.active = false;
        } else {
            this.active = personInputDto.getActive();
        }

        if (personInputDto.getCreatedDate() == null) {
            this.createdDate = new Date();
        } else {
            this.createdDate = personInputDto.getCreatedDate();
        }


    }
}