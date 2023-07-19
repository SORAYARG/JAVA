package com.example.block7crudvalidation.asignature.infraestructure.mapper;

import com.example.block7crudvalidation.asignature.domain.Asignature;
import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureInputDto;
import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AsignatureMapper {
    AsignatureMapper INSTANCE = Mappers.getMapper(AsignatureMapper.class);
    @Mapping(target = "asignature", source = "asignature.asignature")
    AsignatureOutputDto asignatureToAsignatureOutputDto (Asignature asignature);
    Asignature asignatureInputDtoToAsignature (AsignatureInputDto asignatureInputDto);
}
