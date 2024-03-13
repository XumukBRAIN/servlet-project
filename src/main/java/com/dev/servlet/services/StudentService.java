package com.dev.servlet.services;

import com.dev.servlet.configs.InstanceConfiguration;
import com.dev.servlet.models.entity.Student;
import com.dev.servlet.models.dto.StudentDto;
import com.dev.servlet.converters.StudentConverter;
import com.dev.servlet.repositories.StudentRepository;

import java.util.List;
import java.util.stream.Collectors;

public class StudentService {
    private final StudentRepository studentRepository = InstanceConfiguration.getStudentRepositoryInstance();
    private final StudentConverter studentConverter = InstanceConfiguration.getStudentConverterInstance();

    public void save(StudentDto studentDto) {
        Student entity = studentConverter.toEntity(studentDto);
        studentRepository.save(entity);
    }

    public void update(Integer id, StudentDto studentDto) {
        Student entity = studentConverter.toEntity(studentDto);
        studentRepository.update(id, entity);
    }

    public StudentDto getById(Integer id) {
        Student student = studentRepository.getById(id);
        return studentConverter.toDto(student);
    }

    public List<StudentDto> getAll() {
        List<Student> students = studentRepository.getAll();
        return students.stream().map(studentConverter::toDto).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        studentRepository.delete(id);
    }
}