package com.example.block7crudvalidation.person.application;

import com.example.block7crudvalidation.exception.UnprocessableEntityException;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonInputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonStudentOutputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonTeacherOutputDto;
import com.example.block7crudvalidation.person.infraestructure.mapper.PersonMapper;
import com.example.block7crudvalidation.person.infraestructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infraestructure.repository.StudentRepository;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infraestructure.repository.TeacherRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public PersonOutputDto addPerson(PersonInputDto personInputDTO)  {
        PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
        Person persona = new Person(personInputDTO);
        personRepository.save(persona);
        return mapper.personToPersonOutputDto(persona);
    }
    @Override
    public PersonOutputDto getPersonById(Integer id) {
        PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
        Person person = personRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.personToPersonOutputDto(person);
    }

    @Override
    public PersonStudentOutputDto getPersonByIdStudent(int id) {
        PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
        Person person = personRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.personToPersonStudentOutputDto(person);
    }

    @Override
    public PersonTeacherOutputDto getPersonByIdTeacher(int id) {
        PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
        Person person = personRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.
                personToPersonTeacherOutputDto(person);
    }
    @Override
    public List<PersonOutputDto> getPersonName(String username) {
        return personRepository.findAll()
                .stream()
                .filter(person -> person.getUsername().equals(username))
                .map(person -> {
                    PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
                    return mapper.personToPersonOutputDto(person);
                }).toList();
    }
    @Override
    public Iterable<PersonOutputDto> getAllPersons(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest)
                .map(persona -> PersonMapper.INSTANCE.personToPersonOutputDto(persona));
    }

    @Override
    public Iterable getAllPersonFull(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return personRepository.findAll(pageRequest).getContent()
                .stream().map(person -> {
                    PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
                    if (person.getTeacher() != null) {
                        return mapper.personToPersonOutputDto(person);
                    } else if (person.getStudent() != null) {
                        return mapper.personToPersonStudentOutputDto(person);
                    } else {
                        return mapper.personToPersonOutputDto(person);
                    }
                }).toList();
    }

    @Override
    public PersonOutputDto updatePerson(PersonInputDto person, int id) {
        PersonMapper mapper = Mappers.getMapper(PersonMapper.class);
        Person provisionalPerson = personRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        person.setLastname(person.getLastname() != null ? person.getLastname() : provisionalPerson.getLastName());
        person.setImageUrl(person.getImageUrl() != null ? person.getImageUrl() : provisionalPerson.getImageUrl());
        person.setTerminationDate(person.getTerminationDate() != null ? person.getTerminationDate() : provisionalPerson.getTerminationDate());
        Person updatedPerson = new Person(person);
        personRepository.save(updatedPerson);
        return mapper.personToPersonOutputDto(updatedPerson);
    }

    @Override
    public void deletePerson(int id) {
        Person person = personRepository.findById(id)
            .orElseThrow(EntityNotFoundException::new);

        if (person.getStudent() != null) {
            Student student = person.getStudent();
            if (student.getTeacher() != null) {
                Teacher teacherProvisional = teacherRepository
                        .findById(student.getTeacher().getIdteacher())
                        .orElseThrow(EntityNotFoundException::new);
                teacherProvisional.getStudents().remove(student);
            }
        }

        if (person.getTeacher() != null) {
            Teacher teacher = person.getTeacher();
            if (teacher.getStudents() != null) {
                List<Student> students = teacher.getStudents();
                students.forEach(student -> {
                    studentRepository.findById(student.getIdStudent())
                            .orElseThrow(EntityNotFoundException::new)
                            .setTeacher(null);
                });
            }
        }

        personRepository.deleteById(id);
    }
    public String getTypeOfPerson(int id) {
        String typeOfPerson;
        Person person = personRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        if (person.getStudent() != null) {
            typeOfPerson = "Student";
        } else if (person.getTeacher() != null) {
            typeOfPerson = "Teacher";
        } else {
            typeOfPerson = "none";
        }
        return typeOfPerson;
    }

}