package com.example.block7crudvalidation.student.application;

import com.example.block7crudvalidation.student.infraestructure.dto.StudentFullOutputDto;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentInputDto;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutputDto;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {

    StudentOutputDto addStudent (StudentInputDto student);
    StudentOutputDto getStudentById (String id);
    StudentFullOutputDto getStudentByIdFull (String id);
    Iterable<StudentOutputDto> getStudentByUsername (String username);
    Iterable<StudentOutputDto> getAllStudent (int pageNumber, int pageSize);
    StudentOutputDto updateStudent (StudentInputDto student, String id);
    void deleteStudent (String id);
    void addAsignatureToStudent (String idStudent, String idAsignature);
}
