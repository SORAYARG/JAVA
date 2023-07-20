package com.example.block7crudvalidation.student.infraestructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentOutputDto {

    Integer idStudent;
    @NotNull(message = "The number of hours per week should not be null.")
    int numHoursWeek;
    String comments;
    @NotBlank(message = "The branch must not be null")
    String branch;
    Integer personId;
    Integer idTeacher;

}
