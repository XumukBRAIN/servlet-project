package com.dev.servlet.services;

import com.dev.servlet.configs.InstanceConfiguration;
import com.dev.servlet.converters.HomeroomTeacherConverter;
import com.dev.servlet.models.dto.HomeroomTeacherDto;
import com.dev.servlet.models.entity.HomeroomTeacher;
import com.dev.servlet.repositories.HomeroomTeacherRepository;

import java.util.List;
import java.util.Objects;
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
        HomeroomTeacher entity = homeroomTeacherRepository.getByIdWithStudents(id);
        if (entity == null) {
            return new HomeroomTeacherDto();
        }

        return homeroomTeacherConverter.toDto(entity);
    }

    public HomeroomTeacherDto getById(Integer id) {
        HomeroomTeacher entity = homeroomTeacherRepository.getById(id);
        if (entity == null) {
            return new HomeroomTeacherDto();
        }
        return homeroomTeacherConverter.toDto(entity);
    }

    public List<HomeroomTeacherDto> getAll() {
        List<HomeroomTeacher> homeroomTeachers = homeroomTeacherRepository.getAll();
        return homeroomTeachers.stream()
                .filter(Objects::nonNull)
                .map(homeroomTeacherConverter::toDto)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        homeroomTeacherRepository.delete(id);
    }
}