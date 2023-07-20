package com.example.block7crudvalidation.student.domain;
import com.example.block7crudvalidation.asignature.domain.Asignature;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @Column(name = "id_student")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer idStudent;
    @OneToOne
    @JoinColumn(name = "id_person")
    Person person;
    @Column(name = "numHoursWeek")
    int numHoursWeek;
    @Column(name = "comments")
    String comments;
    @Column(name = "branch")
    String branch;
    @ManyToOne
    @JoinColumn(name = "id_teacher")
    Teacher teacher;
    @ManyToMany
    @JoinColumn(name = "id_subject")
    List<Asignature> studySubjects;
}