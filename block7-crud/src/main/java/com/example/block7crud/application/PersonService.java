package com.example.block7crud.application;
import com.example.block7crud.controller.dto.PersonInputDto;
import com.example.block7crud.controller.dto.PersonOutputDto;
import com.example.block7crud.domain.Person;

import java.util.List;

public interface PersonService {


    PersonOutputDto addPerson(PersonInputDto person) throws Exception;

    PersonOutputDto getPersonById(int id);

    void deletePersonById(int id);

    List<PersonOutputDto> getAllPerson();

    List<PersonOutputDto> getPeopleByName(String name);

    PersonOutputDto updatePerson(int id, PersonInputDto person);
}
