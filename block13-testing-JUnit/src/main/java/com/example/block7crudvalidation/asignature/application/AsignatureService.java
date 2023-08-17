package com.example.block7crudvalidation.asignature.application;

import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureInputDto;
import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureOutputDto;

public interface AsignatureService {

    AsignatureOutputDto addAsignature (AsignatureInputDto asignature);
    AsignatureOutputDto getAsignatureById (Integer id);
    Iterable<AsignatureOutputDto> getAllAsignature (int pageNumber, int pageSize);
    Iterable<AsignatureOutputDto> getAsignatureByStudentId (Integer idStudent);
    AsignatureOutputDto updateAsignature (AsignatureInputDto asignature, Integer id);
    void deleteAsignature (Integer id);

}
