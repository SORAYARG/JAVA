package com.example.block7crudvalidation.student.infraestructure.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentInputDto {
    Integer personId;
    Integer idTeacher;
    @NotNull(message = "The number of hours per week should not be null.")
    int numHoursWeek;
    String comments;
    @NotBlank(message = "The branch must not be null")
    String branch;


}
