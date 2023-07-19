package com.example.block7crudvalidation.teacher.infraestructure.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeacherInputDto {
    String idTeacher;
    String comments;
    String branch;
    int idPerson;
}
