package com.example.block13mongodb.application;

import com.example.block13mongodb.controller.dto.PersonInputDto;
import com.example.block13mongodb.controller.dto.PersonOutputDto;
import com.example.block13mongodb.domain.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Component
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PersonOutputDto addPerson (PersonInputDto person){
        Person persona = new Person(person);
        mongoTemplate.save(persona);
        return persona.personToPersonOutputDto();
    }
    @Override
    public PersonOutputDto getPersonId (String id){
        Person persona = mongoTemplate.findById(id, Person.class);
        return persona.personToPersonOutputDto();
    }
    @Override
    public Iterable<PersonOutputDto> getAllPersons (int pageNumber, int pageSize){
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Query query = new Query();
        query.with(pageRequest);
        return mongoTemplate.find(query, Person.class)
                .stream().map(Person::personToPersonOutputDto).toList();
    }
    @Override
    public PersonOutputDto updatePerson (PersonInputDto person, String id){
        Person personaProvisional = mongoTemplate.findById(id, Person.class);
        person.setIdPerson(id);
        person.setFirstname(person.getFirstname() != null ?
                person.getFirstname() : personaProvisional.getFirstname());
        Person persona = new Person(person);
        mongoTemplate.save(persona);
        return persona.personToPersonOutputDto();
    }
    @Override
    public void deletePerson (String id){
        Query query = new Query(Criteria.where("idPerson").is(id));
        mongoTemplate.remove(query, Person.class);
    }
}

