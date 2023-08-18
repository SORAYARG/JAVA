package com.example.block7crudvalidation.person.domain;

import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "person")
@Getter
@Setter
public class Person implements UserDetails {
    @Id
    @GeneratedValue
    private int personId;
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
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Student student;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Teacher teacher;
    private boolean admin;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Person(PersonInputDto personInputDto) throws UnprocessableEntityException {

        if (personInputDto.getUsername() == null) {
            throw new UnprocessableEntityException("Username cannot be null");
        } else if (personInputDto.getUsername().length() > 10) {
            throw new UnprocessableEntityException("Username length cannot be more than 10 characters");
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

        if (personInputDto.getFirstName() == null) {
            throw new UnprocessableEntityException("First Name cannot be null");
        } else {
            this.firstName = personInputDto.getFirstName();
        }

        if (personInputDto.getCompanyEmail() == null) {
            throw new UnprocessableEntityException("Company Email cannot be null");
        } else {
            this.companyEmail = personInputDto.getCompanyEmail();
        }

        if (personInputDto.getPersonalEmail() == null) {
            throw new UnprocessableEntityException("Personal Email cannot be null");
        } else {
            this.personalEmail = personInputDto.getPersonalEmail();
        }

        if (personInputDto.getCity() == null) {
            throw new UnprocessableEntityException("City cannot be null");
        } else {
            this.city = personInputDto.getCity();
        }

        this.active = personInputDto.getActive() != null ? personInputDto.getActive() : false;

        this.createdDate = personInputDto.getCreatedDate() != null ? personInputDto.getCreatedDate() : new Date();
        this.admin = personInputDto.isAdmin();
        this.role = personInputDto.isAdmin() ? Role.ADMIN : Role.USER;

        this.lastName = personInputDto.getLastName();
        this.imageUrl = personInputDto.getImageUrl();
        this.terminationDate = personInputDto.getTerminationDate();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
