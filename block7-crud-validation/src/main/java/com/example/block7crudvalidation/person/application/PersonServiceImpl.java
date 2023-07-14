package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.person.infraestructure.mapper.PersonMapper;
import com.example.block7crudvalidation.person.infraestructure.repository.PersonRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDTO) {
        PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
        Person persona = new Person(personInputDTO);
        personRepository.save(persona);
        return mapper.personToPersonOutputDto(persona);
    }
    @Override
    public PersonOutputDto getPersonById(Integer id) {
        PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
        Person persona = personRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.personToPersonOutputDto(persona);
    }
    @Override
    public List<PersonOutputDto> getPersonName(String name) {
        return personRepository.findAll()
                .stream()
                .filter(person -> person.getName().equals(name))
                .map(person -> {
                    PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
                    return mapper.personToPersonOutputDto(person);
                }).toList();
    }


    @Override
    public void deletePersonById(int id) {
        Person person = personRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PersonOutputDto> getAllPersons() {
       return personRepository.findAll().stream().map(person -> {
           PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
        return mapper.personToPersonOutputDto(person);
    }).toList();
    }


    @Override
    public PersonOutputDto updatePerson(Integer id, PersonInputDto personInputDto) throws RuntimeException {
        Person persona = personRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No username found with ID: " + id));
        persona.update(personInputDto);
        personRepository.save(persona);

        return new PersonOutputDto(persona);
    } }