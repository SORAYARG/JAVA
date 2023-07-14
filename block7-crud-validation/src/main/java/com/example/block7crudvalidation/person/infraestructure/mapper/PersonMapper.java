package com.example.block7crudvalidation.person.infraestructure.mapper;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper Instance = Mappers.getMapper(PersonMapper.class);
    PersonOutputDto personToPersonOutputDto(Person persona);


}