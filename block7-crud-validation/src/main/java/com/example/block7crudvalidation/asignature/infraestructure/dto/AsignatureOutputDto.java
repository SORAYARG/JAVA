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

    Integer idSubject;
    String name;
    String comments;
    Date initialDate;
    Date finishDate;
}
