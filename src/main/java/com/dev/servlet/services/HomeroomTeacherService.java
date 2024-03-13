package com.dev.servlet.services;

import com.dev.servlet.configs.InstanceConfiguration;
import com.dev.servlet.converters.HomeroomTeacherConverter;
import com.dev.servlet.models.dto.HomeroomTeacherDto;
import com.dev.servlet.models.entity.HomeroomTeacher;
import com.dev.servlet.repositories.HomeroomTeacherRepository;

import java.util.List;
import java.util.stream.Collectors;

public class HomeroomTeacherService {
    private final HomeroomTeacherRepository homeroomTeacherRepository = InstanceConfiguration.getHomeroomTeacherRepositoryInstance();
    private final HomeroomTeacherConverter homeroomTeacherConverter = InstanceConfiguration.getHomeroomTeacherConverterInstance();

    public void save(HomeroomTeacherDto homeroomTeacherDto) {
        HomeroomTeacher entity = homeroomTeacherConverter.toEntity(homeroomTeacherDto);
        homeroomTeacherRepository.save(entity);
    }

    public void update(Integer id, HomeroomTeacherDto studentDto) {
        HomeroomTeacher entity = homeroomTeacherConverter.toEntity(studentDto);
        homeroomTeacherRepository.update(id, entity);
    }

    public HomeroomTeacherDto getByIdWithStudents(Integer id) {
        HomeroomTeacher homeroomTeacher = homeroomTeacherRepository.getByIdWithStudents(id);
        return homeroomTeacherConverter.toDto(homeroomTeacher);
    }

    public HomeroomTeacherDto getById(Integer id) {
        HomeroomTeacher student = homeroomTeacherRepository.getById(id);
        return homeroomTeacherConverter.toDto(student);
    }

    public List<HomeroomTeacherDto> getAll() {
        List<HomeroomTeacher> students = homeroomTeacherRepository.getAll();
        return students.stream().map(homeroomTeacherConverter::toDto).collect(Collectors.toList());
    }

    public void delete(Integer id) {
        homeroomTeacherRepository.delete(id);
    }
}