package com.example.block7crudvalidation.teacher.infraestructure.mapper;

import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherFullOutputDto;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherInputDto;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TeacherMapper {

    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);
    TeacherOutputDto teacherToTeacherOutputDto (Teacher teacher);
    @Mapping(target = "personId", source = "person.personId")
    @Mapping(target = "username", source = "teacher.person.username")
    @Mapping(target = "password", source = "teacher.person.password")
    @Mapping(target = "firstName", source = "person.firstName")
    @Mapping(target = "lastName", source = "person.lastName")
    @Mapping(target = "companyEmail", source = "teacher.person.companyEmail")
    @Mapping(target = "personalEmail", source = "teacher.person.personalEmail")
    @Mapping(target = "city", source = "teacher.person.city")
    @Mapping(target = "active", source = "teacher.person.active")
    @Mapping(target = "createdDate", source = "teacher.person.createdDate")
    @Mapping(target = "imageUrl", source = "teacher.person.imageUrl")
    @Mapping(target = "terminationDate", source = "teacher.person.terminationDate")

    TeacherFullOutputDto teacherToTeacherFullOutputDto (Teacher teacher);
    Teacher teacherInputDtoTeacher (TeacherInputDto teacherInputDto);
    Teacher teacherInputDtoToTeacher(TeacherInputDto teacher);
}
