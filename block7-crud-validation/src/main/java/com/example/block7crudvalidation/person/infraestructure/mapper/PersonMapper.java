package com.example.block7crudvalidation.person.infraestructure.mapper;

import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonOutputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonStudentOutputDto;
import com.example.block7crudvalidation.person.infraestructure.dto.PersonTeacherOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);
    PersonOutputDto personToPersonOutputDto (Person person);
    @Mapping(target = "idStudent", source = "person.student.idStudent")
    @Mapping(target = "numHoursWeek", source = "person.student.numHoursWeek")
    @Mapping(target = "comments", source = "person.student.comments")
    @Mapping(target = "branch", source = "person.student.branch")
    PersonStudentOutputDto personToPersonStudentOutputDto (Person person);
    @Mapping(target = "idTeacher", source = "teacher.idteacher")
    @Mapping(target = "comments", source = "person.teacher.comments")
    @Mapping(target = "branch", source = "person.teacher.branch")
    PersonTeacherOutputDto personToPersonTeacherOutputDto (Person person);

}