package com.example.block7crudvalidation.asignature.domain;
import com.example.block7crudvalidation.student.domain.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "asignature")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Asignature {

    @Id
    @Column(name = "id_study")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    String idStudy;

    @ManyToMany
    @JoinColumn(name = "idStudent")
    List<Student> student;
    @Column(name = "asignature")
    String asignature;
    @Column(name = "comments")
    String comments;
    @Column(name = "initial-date")
    Date initialDate;
    @Column(name = "final-date")
    Date finishDate;

}
