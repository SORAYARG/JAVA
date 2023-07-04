package com.example.block7crud.controller.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonOutputDto {
    int id;
    String name;
    int age;
    String town;
}