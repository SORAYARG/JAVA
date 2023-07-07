package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;

import java.util.List;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto personInputDto);
    PersonOutputDto getPersonById(Integer id);
    List<PersonOutputDto> getPersonName(String name);
    List<PersonOutputDto> getPersons();
    PersonOutputDto updatePerson(Integer id, PersonInputDto personInputDto);
    void deletePersonById(int id);

}

