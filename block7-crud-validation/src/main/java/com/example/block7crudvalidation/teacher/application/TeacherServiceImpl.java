package com.example.block7crudvalidation.teacher.application;

import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infraestructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.infraestructure.repository.StudentRepository;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherFullOutputDto;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherInputDto;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutputDto;
import com.example.block7crudvalidation.teacher.infraestructure.mapper.TeacherMapper;
import com.example.block7crudvalidation.teacher.infraestructure.repository.TeacherRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public TeacherOutputDto addTeacher(@Valid TeacherInputDto teacher) {
        TeacherMapper mapper = Mappers.getMapper(TeacherMapper.class);
        Person person = personRepository.findById(teacher.getIdPerson())
                .orElseThrow(EntityNotFoundException::new);
        Teacher profesor = mapper.teacherInputDtoTeacher(teacher);
        person.setTeacher(profesor);
        profesor.setPerson(person);
        teacherRepository.save(profesor);
        return mapper.teacherToTeacherOutputDto(profesor);
    }

    @Override
    public TeacherOutputDto getTeacherById(String id) {
        TeacherMapper mapper = Mappers.getMapper(TeacherMapper.class);
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.teacherToTeacherOutputDto(teacher);
    }

    @Override
    public TeacherFullOutputDto getTeacherByIdFull(String id) {
        TeacherMapper mapper = Mappers.getMapper(TeacherMapper.class);
        Teacher teacher =  teacherRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.teacherToTeacherFullOutputDto(teacher);
    }
    @Override
    public Iterable<TeacherOutputDto> getTeacherByName(String name) {
        List<Integer> idList = personRepository.findAll()
                .stream()
                .filter(person -> person.getFirstName().equals(name))
                .map(Person::getPersonId)
                .toList();
        TeacherMapper mapper = Mappers.getMapper(TeacherMapper.class);
        return teacherRepository.findAll()
                .stream()
                .filter(teacher -> idList.contains(teacher.getPerson().getPersonId()))
                .map(mapper::teacherToTeacherOutputDto)
                .toList();
    }
    @Override
    public Iterable<TeacherOutputDto> getAllTeachers(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return teacherRepository.findAll(pageRequest).getContent().stream()
                .map(teacher -> {
                    TeacherMapper mapper = Mappers.getMapper(TeacherMapper.class);
                    return mapper.teacherToTeacherOutputDto(teacher);
                }).toList();
    }


    @Override
    public TeacherOutputDto updateTeacher(@Valid TeacherInputDto teacher, String id) {
        TeacherMapper mapper = Mappers.getMapper(TeacherMapper.class);
        Teacher teacherProvisional = teacherRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        teacher.setComments(teacher.getComments() != null ? teacher.getComments() : teacherProvisional.getComments());
        Teacher teacher1 = mapper.teacherInputDtoToTeacher(teacher);
        teacher1.setIdteacher(id);
        if (teacher.getIdPerson() != 0) {
            int idPersonOriginal = teacherProvisional.getPerson().getPersonId();
            personRepository.findById(idPersonOriginal)
                    .orElseThrow(EntityNotFoundException::new)
                    .setTeacher(null);
            Person personProvisional = personRepository.findById(teacher.getIdPerson())
                    .orElseThrow(EntityNotFoundException::new);
            teacher1.setPerson(personProvisional);
            personProvisional.setTeacher(teacher1);
            personProvisional.setPersonId(personProvisional.getPersonId());
            personRepository.save(personProvisional);
        } else {
            teacher1.setPerson(teacherProvisional.getPerson());
        }
        teacherRepository.save(teacher1);
        return mapper.teacherToTeacherOutputDto(teacher1);
    }


    @Override
    public void deleteTeacher(String id) {
        Teacher provisionalTeacher = teacherRepository
                .findById(id)
                .orElseThrow(EntityNotFoundException::new);

        if (provisionalTeacher.getPerson() != null) {
            Person provisionalPerson = personRepository
                    .findById(provisionalTeacher.getPerson().getPersonId())
                    .orElseThrow(EntityNotFoundException::new);
            provisionalPerson.setTeacher(null);
        }

        if (provisionalTeacher.getStudents() != null) {
            provisionalTeacher.getStudents().forEach(student -> {
                studentRepository
                        .findById(student.getIdStudent())
                        .orElseThrow(EntityNotFoundException::new)
                        .setTeacher(null);
            });
        }

        teacherRepository.delete(provisionalTeacher);
    }

}
