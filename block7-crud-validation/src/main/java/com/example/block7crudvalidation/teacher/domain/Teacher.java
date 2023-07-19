package com.example.block7crudvalidation.teacher.domain;

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
@Table(name = "teacher")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Teacher {
    @Id
    @Column(name = "id_teacher")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    String idteacher;
    @OneToOne
    @JoinColumn(name = "idPerson")
    Person person;
    @OneToMany
    List<Student> students;
    @Column(name = "comments")
    String comments;
    @Column(name = "branch")
    String branch;
}
