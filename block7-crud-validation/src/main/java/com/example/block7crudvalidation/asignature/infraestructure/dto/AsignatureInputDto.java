package com.example.block7crudvalidation.asignature.infraestructure.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AsignatureInputDto {

    String idStudy;
    String idStudent;
    @NotNull(message = "La asignatura no debe ser nula")
    String asignature;
    String comments;
    @NotNull(message = "La fecha inicial no puede ser nula")
    Date initialDate;
    Date finishDate;
}
