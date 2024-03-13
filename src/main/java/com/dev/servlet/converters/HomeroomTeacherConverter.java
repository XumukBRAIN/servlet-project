package com.dev.servlet.converters;

import com.dev.servlet.configs.InstanceConfiguration;
import com.dev.servlet.models.dto.HomeroomTeacherDto;
import com.dev.servlet.models.entity.HomeroomTeacher;

import java.util.Collections;
import java.util.stream.Collectors;

public class HomeroomTeacherConverter {

    private final StudentConverter studentConverter = InstanceConfiguration.getStudentConverterInstance();

    public HomeroomTeacher toEntity(HomeroomTeacherDto homeroomTeacherDto) {
        if (homeroomTeacherDto == null) {
            throw new IllegalArgumentException("Не передан объект homeroomTeacherDto для конвертации");
        }

        HomeroomTeacher homeroomTeacher = new HomeroomTeacher();
        homeroomTeacher.setFirstName(homeroomTeacherDto.getFirstName());
        homeroomTeacher.setSecondName(homeroomTeacherDto.getSecondName());
        homeroomTeacher.setPatronymic(homeroomTeacherDto.getPatronymic());
        homeroomTeacher.setSpecialization(homeroomTeacherDto.getSpecialization());
        if (homeroomTeacherDto.getStudents() != null) {
            homeroomTeacher.setStudents(homeroomTeacherDto.getStudents().stream().map(studentConverter::toEntity).collect(Collectors.toList()));
        } else {
            homeroomTeacher.setStudents(Collections.emptyList());
        }

        return homeroomTeacher;
    }

    public HomeroomTeacherDto toDto(HomeroomTeacher homeroomTeacher) {
        if (homeroomTeacher == null) {
            throw new IllegalArgumentException("Не передан объект homeroomTeacher для конвертации");
        }

        HomeroomTeacherDto homeroomTeacherDto = new HomeroomTeacherDto();
        homeroomTeacherDto.setFirstName(homeroomTeacher.getFirstName());
        homeroomTeacherDto.setSecondName(homeroomTeacher.getSecondName());
        homeroomTeacherDto.setPatronymic(homeroomTeacher.getPatronymic());
        homeroomTeacherDto.setSpecialization(homeroomTeacher.getSpecialization());
        if (homeroomTeacher.getStudents() != null) {
            homeroomTeacherDto.setStudents(homeroomTeacher.getStudents().stream().map(studentConverter::toDto).collect(Collectors.toList()));
        } else {
            homeroomTeacherDto.setStudents(Collections.emptyList());
        }

        return homeroomTeacherDto;
    }
}