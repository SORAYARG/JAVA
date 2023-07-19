package com.example.block7crudvalidation.asignature.infraestructure.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AsignatureOutputDto {

    String idStudy;
    String asignature;
    String comments;
    Date initialDate;
    Date finishDate;
}
