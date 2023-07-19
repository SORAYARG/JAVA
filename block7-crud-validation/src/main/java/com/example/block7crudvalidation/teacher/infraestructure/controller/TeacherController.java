package com.example.block7crudvalidation.teacher.infraestructure.controller;

import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.teacher.application.TeacherService;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherInputDto;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/teacher")

public class TeacherController {
    @Autowired
    TeacherService teacherService;
    @PostMapping
    public ResponseEntity<TeacherOutputDto> addTeacher (@Valid @RequestBody TeacherInputDto teacher){
        URI location = URI.create("/teacher");
        try {
            return ResponseEntity.created(location).body(teacherService.addTeacher(teacher));
        } catch (EntityNotFoundException e){
            throw new EntityNotFoundException();
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity getTeacherById (@PathVariable String id,
                                          @RequestParam(defaultValue =  "simple", required = false)
                                           String outputType){
        if(outputType.equals("simple")) {
            return ResponseEntity.ok().body(teacherService.getTeacherById(id));
        } else if (outputType.equals("full")) {
            return ResponseEntity.ok().body(teacherService.getTeacherByIdFull(id));
        } else {
            return ResponseEntity.unprocessableEntity().body("outputType debe ser 'simple' o 'full'");
        }
    }
    @GetMapping("/username/{username}")
    public Iterable<TeacherOutputDto> getTeacherByName (@PathVariable String name){
        return teacherService.getTeacherByName(name);
    }
    @GetMapping
    public Iterable<TeacherOutputDto> getAllTeachers (@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                       @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return teacherService.getAllTeachers(pageNumber, pageSize);
    }
    @PutMapping("/{id}")
    public ResponseEntity<TeacherOutputDto> updateTeacher (@Valid @RequestBody TeacherInputDto teacher,
                                                             @PathVariable String id){
        teacherService.getTeacherById(id);
        return ResponseEntity.ok().body(teacherService.updateTeacher(teacher, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTeacher (@PathVariable String id){
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().body("Se ha borrado el profesor con id: " + id);
    }
}
