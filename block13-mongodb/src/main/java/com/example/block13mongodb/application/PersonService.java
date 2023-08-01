package com.example.block13mongodb.application;

import com.example.block13mongodb.controller.dto.PersonInputDto;
import com.example.block13mongodb.controller.dto.PersonOutputDto;


public interface PersonService {

    PersonOutputDto addPerson (PersonInputDto person);
    PersonOutputDto getPersonId (String id);
    Iterable<PersonOutputDto> getAllPersons (int pageNumber, int pageSize);
    PersonOutputDto updatePerson (PersonInputDto person, String id);
    void deletePerson (String id);
}
