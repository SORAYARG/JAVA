package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonStudentOutputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonTeacherOutputDto;

import java.util.List;

public interface PersonService {

    PersonOutputDto addPerson(PersonInputDto personInputDto);
    PersonOutputDto getPersonById(Integer id);
    PersonStudentOutputDto getPersonByIdStudent(Integer id);
    PersonTeacherOutputDto getPersonByIdTeacher(Integer id);
    List<PersonOutputDto> getPersonName(String username);
    Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize);
    Iterable getAllPersonFull (int pageNumber, int pageSize);
    PersonOutputDto updatePerson(PersonInputDto person, Integer id);
    void deletePerson(Integer id);

}

