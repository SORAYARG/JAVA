package com.example.block7crudvalidation.teacher.application;

import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherFullOutputDto;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherInputDto;
import com.example.block7crudvalidation.teacher.infraestructure.dto.TeacherOutputDto;

public interface TeacherService {

        TeacherOutputDto addTeacher(TeacherInputDto teacher);
        TeacherOutputDto getTeacherById(Integer id);
        TeacherFullOutputDto getTeacherByIdFull (Integer id);
        Iterable<TeacherOutputDto> getTeacherByName(String name);
        Iterable<TeacherOutputDto> getAllTeachers(int pageNumber, int pageSize);
        TeacherOutputDto updateTeacher(TeacherInputDto teacher, Integer id);
        void deleteTeacher(Integer id);

    }

