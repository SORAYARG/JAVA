package com.example.block7crudvalidation.student.application;

import com.example.block7crudvalidation.asignature.domain.Asignature;
import com.example.block7crudvalidation.asignature.infraestructure.repository.AsignatureRepository;
import com.example.block7crudvalidation.person.domain.Person;
import com.example.block7crudvalidation.person.infraestructure.repository.PersonRepository;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentFullOutputDto;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentInputDto;
import com.example.block7crudvalidation.student.infraestructure.dto.StudentOutputDto;
import com.example.block7crudvalidation.student.infraestructure.mapper.StudentMapper;
import com.example.block7crudvalidation.student.infraestructure.repository.StudentRepository;
import com.example.block7crudvalidation.teacher.domain.Teacher;
import com.example.block7crudvalidation.teacher.infraestructure.repository.TeacherRepository;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import javax.validation.Valid;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    PersonRepository personRepository;
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    AsignatureRepository asignatureRepository;

    @Override
    public StudentOutputDto addStudent(@Valid StudentInputDto student) {
        StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
        Person person = personRepository.findById(student.getPersonId())
                .orElseThrow(EntityNotFoundException::new);
        Teacher teacher = null;
        Student student1 = mapper.studentInputDtoToStudent(student);
        if(student.getIdTeacher() != null){
            teacher = teacherRepository.findById(student.getIdTeacher())
                    .orElseThrow(EntityNotFoundException::new);
            teacher.getStudents().add(student1);
        }
        person.setStudent(student1);
        student1.setPerson(person);
        student1.setTeacher(teacher);
        studentRepository.save(student1);
        return mapper.studentToStudentOutputDto(student1);
    }

    @Override
    public StudentOutputDto getStudentById(Integer id) {
        Student student1 = studentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
        return mapper.studentToStudentOutputDto(student1);
    }

    @Override
    public StudentFullOutputDto getStudentByIdFull(Integer id) {
        Student student1 = studentRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
        return mapper.studentToStudentFullOutputDto(student1);
    }

    @Override
    public Iterable<StudentOutputDto> getStudentByUsername(String username) {
        List<Integer> idList = personRepository.findAll().stream()
                .filter(person -> person.getUsername().equals(username))
                .map(Person::getPersonId)
                .toList();

        return studentRepository.findAll().stream().filter(student -> {
            boolean check = false;
            for(Integer id : idList){
                if(student.getPerson().getPersonId() == id){
                    check = true;
                }
            }
            return check;
        }).map(student -> {
            StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
            return mapper.studentToStudentOutputDto(student);
        }).toList();
    }

    @Override
    public Iterable<StudentOutputDto> getAllStudent(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return studentRepository.findAll(pageRequest).getContent().stream()
                .map(student -> {
                    StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
                    return mapper.studentToStudentOutputDto(student);
                }).toList();
    }

    @Override
    public StudentOutputDto updateStudent(@Valid StudentInputDto student, Integer id) {
        StudentMapper mapper = Mappers.getMapper(StudentMapper.class);
        Student studentProvisional = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        student.setComments(student.getComments() != null ?
                student.getComments() : studentProvisional.getComments());
        Student student1 = mapper.studentInputDtoToStudent(student);
        student1.setIdStudent(id);
        student1.setStudySubjects(studentProvisional.getStudySubjects());
        if (student.getPersonId() != 0){
            int originalPersonId = studentProvisional.getPerson().getPersonId();
            personRepository.findById(originalPersonId)
                    .orElseThrow(EntityNotFoundException::new)
                    .setStudent(null);
            Person provisionalPerson = personRepository.findById(student.getPersonId())
                    .orElseThrow(EntityNotFoundException::new);
            student1.setPerson(provisionalPerson);
            provisionalPerson.setStudent(student1);
        } else {
            student1.setPerson(studentProvisional.getPerson());
        }
        if (student.getIdTeacher() != null){
            if(studentProvisional.getTeacher() != null){
                Integer originalTeacherId = studentProvisional.getTeacher().getIdTeacher();
                teacherRepository.findById(originalTeacherId)
                        .orElseThrow(EntityNotFoundException::new)
                        .getStudents()
                        .remove(studentProvisional);
            }
            Teacher provisionalTeacher = teacherRepository.findById(student.getIdTeacher())
                    .orElseThrow(EntityNotFoundException::new);
            student1.setTeacher(provisionalTeacher);
            provisionalTeacher.getStudents().add(student1);
        } else {
            student1.setTeacher(studentProvisional.getTeacher());
        }
        studentRepository.save(student1);
        return mapper.studentToStudentOutputDto(student1);
    }

    @Override
    public void deleteStudent(Integer id) {
        Student studentProvisional = studentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (studentProvisional.getPerson() != null) {
            Person provisionalPerson = personRepository
                    .findById(studentProvisional.getPerson().getPersonId())
                    .orElseThrow(EntityNotFoundException::new);
            provisionalPerson.setStudent(null);
        }
        if(studentProvisional.getTeacher() != null){
            Teacher provisionalTeacher = teacherRepository
                    .findById(studentProvisional.getTeacher().getIdTeacher())
                    .orElseThrow(EntityNotFoundException::new);
            provisionalTeacher.getStudents().remove(studentProvisional);
        }
        if(studentProvisional.getStudySubjects() != null){
            List<Integer> subjectIds = studentProvisional.getStudySubjects()
                    .stream()
                    .map(Asignature::getIdSubject)
                    .toList();
            List<Asignature> asignatures = asignatureRepository.findAll()
                    .stream()
                    .filter(asignature -> {
                        boolean check = false;
                        for(Integer idStudy : subjectIds){
                            if(asignature.getIdSubject().equals(idStudy)){
                                check = true;
                            }
                        }
                        return check;
                    }).toList();
            asignatures.forEach(asignature -> asignature.getStudents().remove(studentProvisional));
            asignatures.forEach(asignature -> asignatureRepository.save(asignature));
        }
        studentRepository.delete(studentProvisional);
    }

    @Override
    public void addAsignatureToStudent(Integer studentId, Integer asignatureId) {
        Asignature asignature = asignatureRepository.findById(asignatureId).orElseThrow();
        Student student = studentRepository.findById(studentId).orElseThrow();
        student.getStudySubjects().add(asignature);
        asignature.getStudents().add(student);
        studentRepository.save(student);
        asignatureRepository.save(asignature);
    }
}
