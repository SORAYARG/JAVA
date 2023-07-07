package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;
import com.example.block7crudvalidation.person.infraestructure.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDTO) {
        Person person = new Person(personInputDTO);
        personRepository.save(person);

        return new PersonOutputDto(person);
    }
    @Override
    public PersonOutputDto getPersonById(Integer id) {
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            return new PersonOutputDto(optionalPerson.get());
        } else {
            throw new RuntimeException("No person found with ID: " + id);
        }
    }


    @Override
    public void deletePersonById(int id) {
        personRepository.deleteById(id);
    }

    @Override
    public List<PersonOutputDto> getPersons() {
        List<PersonOutputDto> personOutputList = new ArrayList<>();
        personRepository.findAll().forEach(person -> personOutputList.add(new PersonOutputDto(person)));
        return personOutputList;
    }

    @Override
    public List<PersonOutputDto> getPersonName(String username) {
        List<PersonOutputDto> personOutputList = new ArrayList<>();
        personRepository.findByName(username).forEach(person -> personOutputList.add(new PersonOutputDto(person)));
        return personOutputList;
    }

    @Override
    public PersonOutputDto updatePerson(Integer id, PersonInputDto personInputDto) throws RuntimeException {
        Person persona = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No username found with ID: " + id));
        persona.update(personInputDto);
        personRepository.save(persona);

        return new PersonOutputDto(persona);
    } }