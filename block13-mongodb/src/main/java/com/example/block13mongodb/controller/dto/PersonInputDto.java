package com.example.block13mongodb.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonInputDto {

    String idPerson;
    String username;
    String password;
    String name;
    String firstname;

}
