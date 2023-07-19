package com.example.block7crudvalidation.asignature.application;
import com.example.block7crudvalidation.asignature.domain.Asignature;
import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureInputDto;
import com.example.block7crudvalidation.asignature.infraestructure.dto.AsignatureOutputDto;
import com.example.block7crudvalidation.asignature.infraestructure.mapper.AsignatureMapper;
import com.example.block7crudvalidation.asignature.infraestructure.repository.AsignatureRepository;
import com.example.block7crudvalidation.exception.EntityNotFoundException;
import com.example.block7crudvalidation.student.domain.Student;
import com.example.block7crudvalidation.student.infraestructure.repository.StudentRepository;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
public class AsignatureServiceImpl implements AsignatureService {
    @Autowired
    AsignatureRepository asignatureRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public AsignatureOutputDto addAsignature(@Valid AsignatureInputDto asignature) {
        AsignatureMapper mapper = Mappers.getMapper(AsignatureMapper.class);
        Asignature asignature1 = mapper.asignatureInputDtoToAsignature(asignature);
        asignatureRepository.save(asignature1);
        return mapper.asignatureToAsignatureOutputDto(asignature1);
    }

    @Override
    public AsignatureOutputDto getAsignatureById(String id) {
        AsignatureMapper mapper = Mappers.getMapper(AsignatureMapper.class);
        Asignature asignature = asignatureRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return mapper.asignatureToAsignatureOutputDto(asignature);
    }

    @Override
    public Iterable<AsignatureOutputDto> getAllAsignature(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return asignatureRepository.findAll(pageRequest).getContent().stream()
                .map(asignature -> {
                    AsignatureMapper mapper = Mappers.getMapper(AsignatureMapper.class);
                    return mapper.asignatureToAsignatureOutputDto(asignature);
                }).toList();
    }

    @Override
    public Iterable<AsignatureOutputDto> getAsignatureByStudentId(String idStudent) {
        Student student = studentRepository.findById(idStudent).orElseThrow();
        return asignatureRepository.findAll().stream()
                .filter(asignature -> asignature.getStudent()
                        .contains(student))
                .map(asignature -> {
                    AsignatureMapper mapper = Mappers.getMapper(AsignatureMapper.class);
                    return mapper.asignatureToAsignatureOutputDto(asignature);
                }).toList();
    }

    @Override
    public AsignatureOutputDto updateAsignature(@Valid AsignatureInputDto asignature, String id) {
        AsignatureMapper mapper = Mappers.getMapper(AsignatureMapper.class);
        Asignature asignatureProvisional = asignatureRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        Asignature asignature1 = mapper.asignatureInputDtoToAsignature(asignature);
        asignature1.setIdStudy(asignatureProvisional.getIdStudy());
        if (asignature.getIdStudent() != null) {
            List<String> idStudentOriginal = asignatureProvisional.getStudent()
                    .stream()
                    .map(Student::getIdStudent)
                    .toList();
            List<Student> provisionalStudents = studentRepository.findAll()
                    .stream()
                    .filter(student -> {
                        boolean check = false;
                        for (String idStudent : idStudentOriginal) {
                            if (student.getIdStudent().equals(idStudent)) {
                                check = true;
                            }
                        }
                        return check;
                    }).toList();
            provisionalStudents.forEach(student -> student.getStudySubjects().remove(asignatureProvisional));
            provisionalStudents.forEach(student -> studentRepository.save(student));
            Student student1 = studentRepository.findById(asignature.getIdStudent())
                    .orElseThrow(EntityNotFoundException::new);
            asignature1.getStudent().add(student1);
            student1.getStudySubjects().add(asignature1);
            studentRepository.save(student1);
        } else {
            asignature1.setStudent(asignatureProvisional.getStudent());
        }
        asignatureRepository.save(asignature1);
        return mapper.asignatureToAsignatureOutputDto(asignature1);
    }

    @Override
    public void deleteAsignature(String id) {
        Asignature asignature = asignatureRepository.findById(id).orElseThrow();
        List<Student> students = asignatureRepository.findById(id).orElseThrow().getStudent();
        students.forEach(student -> student.getStudySubjects().remove(asignature));
        students.forEach(student -> studentRepository.save(student));
        asignatureRepository.deleteById(id);
    }
}