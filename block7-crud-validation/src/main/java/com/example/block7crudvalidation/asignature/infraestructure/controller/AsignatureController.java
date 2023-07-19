package com.example.block7crudvalidation.asignature.infraestructure.controller;

import com.example.block7crudvalidation.asignature.application.AsignatureService;
import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureInputDto;
import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureOutputDto;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.student.application.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/asignature")
public class AsignatureController {
    @Autowired
    AsignatureService asignatureService;
    @Autowired
    StudentService studentService;

    @PostMapping
    public ResponseEntity<AsignatureOutputDto> addAsignature(@Valid @RequestBody AsignatureInputDto asignature) {
        URI location = URI.create("/asignature");
        return ResponseEntity.created(location).body(asignatureService.addAsignature(asignature));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AsignatureOutputDto> getAsignatureById(@PathVariable String id) {
        try {
            return ResponseEntity.ok().body(asignatureService.getAsignatureById(id));
        } catch (EntityNotFoundException e) {
            throw new EntityNotFoundException();
        }
    }

    @GetMapping
    public Iterable<AsignatureOutputDto> getAllAsignature(@RequestParam(defaultValue = "0", required = false) int pageNumber,
                                                          @RequestParam(defaultValue = "4", required = false) int pageSize) {
        return asignatureService.getAllAsignature(pageNumber, pageSize);
    }

    @GetMapping("/studentid/{idStudent}")
    public Iterable<AsignatureOutputDto> getAsignatureByStudentId(@PathVariable String idStudent) {
        studentService.getStudentById(idStudent);
        return asignatureService.getAsignatureByStudentId(idStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AsignatureOutputDto> updateAsignature(@Valid @RequestBody AsignatureInputDto asignature, @PathVariable String id) {
        asignatureService.getAsignatureById(id);
        return ResponseEntity.ok().body(asignatureService.updateAsignature(asignature, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsignature(@PathVariable String id) {
        asignatureService.deleteAsignature(id);
        return ResponseEntity.ok().body("Se ha borrado la asignatura con id: " + id);
    }
}
