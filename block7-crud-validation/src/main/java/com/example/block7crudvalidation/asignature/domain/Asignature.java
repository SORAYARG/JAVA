package com.example.block7crudvalidation.asignature.domain;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "subjects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignature {

    @Id
    @Column(name = "id_subject")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Integer idSubject;

    @ManyToOne
    @JoinColumn(name = "id_teacher")
    Teacher teacher;

    @ManyToMany
    @JoinColumn(name = "id_student")
    List<Student> students;
    @Column
    String name;
    @Column(name = "comments")
    String comments;
    @Column(name = "initialDate")
    Date initialDate;
    @Column(name = "finishDate")
    Date finishDate;

}
