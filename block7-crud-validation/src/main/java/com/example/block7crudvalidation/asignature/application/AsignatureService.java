package com.example.block7crudvalidation.asignature.application;

import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureInputDto;
import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureOutputDto;

public interface AsignatureService {

    AsignatureOutputDto addAsignature (AsignatureInputDto asignature);
    AsignatureOutputDto getAsignatureById (String id);
    Iterable<AsignatureOutputDto> getAllAsignature (int pageNumber, int pageSize);
    Iterable<AsignatureOutputDto> getAsignatureByStudentId (String idStudent);
    AsignatureOutputDto updateAsignature (AsignatureInputDto asignature, String id);
    void deleteAsignature (String id);

}
