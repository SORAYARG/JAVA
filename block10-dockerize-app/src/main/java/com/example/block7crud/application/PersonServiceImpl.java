package com.example.block7crud.application;

import com.example.block7crud.controller.dto.PersonInputDto;
import com.example.block7crud.controller.dto.PersonOutputDto;
import com.example.block7crud.domain.Person;
import com.example.block7crud.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;


    @Override
    public PersonOutputDto addPerson(PersonInputDto person) {
        return personRepository.save(new Person(person)).personToPersonOutputDto();
    }

    @Override
    public PersonOutputDto getPersonById(int id) {
        return personRepository.findById(id).orElseThrow().personToPersonOutputDto();
    }

    @Override
    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<PersonOutputDto> getAllPerson() {
        return personRepository.findAll().stream().map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public List<PersonOutputDto> getPeopleByName(String name) {
        return personRepository.findByName(name).stream().map(Person::personToPersonOutputDto).toList();
    }

    @Override
    public PersonOutputDto updatePerson(int id, PersonInputDto person) {
        Person p = personRepository.findById(id).orElseThrow();

        if (person.getName() != null) p.setName(person.getName());
        if (person.getAge() != 0) p.setAge(person.getAge());
        if (person.getTown() != null) p.setTown(person.getTown());

        return personRepository.save(p).personToPersonOutputDto();
    }
}