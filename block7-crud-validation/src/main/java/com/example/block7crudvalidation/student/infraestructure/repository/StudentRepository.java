package com.example.block7crudvalidation.student.infraestructure.repository;

import com.example.block7crudvalidation.student.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String> {
}