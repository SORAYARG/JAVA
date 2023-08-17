package com.example.block7crudvalidation.student.infraestructure.mapper;

import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentFullOutputDto;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentInputDto;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StudentMapper {
    StudentMapper INSTANCE = Mappers.getMapper(StudentMapper.class);
    @Mapping(target = "idTeacher", source = "student.teacher.idTeacher")
    StudentOutputDto studentToStudentOutputDto (Student student);

    @Mapping(target = "personId", source = "student.person.personId")
    @Mapping(target = "username", source = "student.person.username")
    @Mapping(target = "password", source = "student.person.password")
    @Mapping(target = "firstName", source = "student.person.firstName")
    @Mapping(target = "lastName", source = "student.person.lastName")
    @Mapping(target = "companyEmail", source = "student.person.companyEmail")
    @Mapping(target = "personalEmail", source = "student.person.personalEmail")
    @Mapping(target = "city", source = "student.person.city")
    @Mapping(target = "active", source = "student.person.active")
    @Mapping(target = "createdDate", source = "student.person.createdDate")
    @Mapping(target = "imageUrl", source = "student.person.imageUrl")
    @Mapping(target = "terminationDate", source = "student.person.terminationDate")

    StudentFullOutputDto studentToStudentFullOutputDto (Student student);
    Student studentInputDtoToStudent (StudentInputDto studentInputDto);
}