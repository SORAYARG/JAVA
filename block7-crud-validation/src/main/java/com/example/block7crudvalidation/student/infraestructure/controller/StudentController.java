package com.example.block7crudvalidation.student.infraestructure.controller;

import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.student.application.StudentService;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentInputDto;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@Valid @RequestBody StudentInputDto student) {
        URI location = URI.create("/student");
        try {
            return ResponseEntity.created(location).body(studentService.addStudent(student));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudentById(@PathVariable Integer id,
                                            @RequestParam(defaultValue = "simple", required = false) String outputType) {
        if (outputType.equals("simple")) {
            return ResponseEntity.ok().body(studentService.getStudentById(id));
        } else if (outputType.equals("full")) {
            return ResponseEntity.ok().body(studentService.getStudentByIdFull(id));
        } else {
            return ResponseEntity.unprocessableEntity().body("outputType must be 'simple' or 'full' ");
        }
    }

    @GetMapping("/username/{username}")
    public Iterable<StudentOutputDto> getStudentByUsername(@PathVariable String username) {
        return studentService.getStudentByUsername(username);
    }

    @GetMapping
    public Iterable<StudentOutputDto> getAllStudent(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                    @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return studentService.getAllStudent(pageNumber, pageSize);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentOutputDto> updateStudent(@RequestBody StudentInputDto student, @PathVariable Integer id) {
        studentService.getStudentById(id);
        return ResponseEntity.ok().body(studentService.updateStudent(student, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().body("Student with id has been deleted: " + id);
    }

    @PutMapping("/{idStudent}/{idAsignature}")
    public ResponseEntity<String> addAsignatureToStudent(@PathVariable Integer idStudent, @PathVariable Integer idAsignature) {
        studentService.getStudentById(idStudent);
        studentService.addAsignatureToStudent(idStudent, idAsignature);
        return ResponseEntity.ok().body("The subject has been added with id " + idAsignature + " to the student with id " + idStudent);
    }
}