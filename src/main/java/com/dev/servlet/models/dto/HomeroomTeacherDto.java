package com.dev.servlet.models.dto;

import java.util.List;

public class HomeroomTeacherDto {
    private String firstName;
    private String secondName;
    private String patronymic;
    private String specialization;
    private List<StudentDto> students;

    public HomeroomTeacherDto() {}

    public HomeroomTeacherDto(String firstName, String secondName, String patronymic, String specialization, List<StudentDto> students) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.specialization = specialization;
        this.students = students;
    }

    public HomeroomTeacherDto(String firstName, String secondName, String patronymic, String specialization) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.specialization = specialization;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<StudentDto> getStudents() {
        return students;
    }

    public void setStudents(List<StudentDto> students) {
        this.students = students;
    }
}
