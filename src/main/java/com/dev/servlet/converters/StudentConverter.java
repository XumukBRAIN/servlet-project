package com.dev.servlet.converters;

import com.dev.servlet.models.entity.Student;
import com.dev.servlet.models.dto.StudentDto;

public class StudentConverter {

    public Student toEntity(StudentDto studentDto) {
        if (studentDto == null) {
            throw new IllegalArgumentException("Не передан объект StudentDto для конвертации");
        }

        Student student = new Student();
        student.setFirstName(studentDto.getFirstName());
        student.setSecondName(studentDto.getSecondName());
        student.setPatronymic(studentDto.getPatronymic());
        student.setBirthdate(studentDto.getBirthdate());
        student.setFaculty(studentDto.getFaculty());

        return student;
    }

    public StudentDto toDto(Student student) {
        if (student == null) {
            throw new IllegalArgumentException("Не передан объект Student для конвертации");
        }

        StudentDto studentDto = new StudentDto();
        student.setFirstName(student.getFirstName());
        student.setSecondName(student.getSecondName());
        student.setPatronymic(student.getPatronymic());
        student.setBirthdate(student.getBirthdate());
        student.setFaculty(student.getFaculty());

        return studentDto;
    }
}