package com.example.block7crudvalidation.teacher.domain;

import com.example.block7crudvalidation.asignature.domain.Asignature;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Teacher {
    @Id
    @Column(name = "id_teacher")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer idTeacher;
    @OneToOne
    @JoinColumn(name = "id_person")
    Person person;
    @OneToMany
    List<Student> students;
    @Column(name = "comments")
    String comments;
    @Column(name = "branch")
    String branch;
    @OneToMany
    List<Asignature> subjects;
}
