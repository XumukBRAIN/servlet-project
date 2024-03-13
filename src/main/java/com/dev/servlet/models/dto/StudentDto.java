package com.dev.servlet.models.dto;

import java.util.Date;

public class StudentDto {
    private String firstName;
    private String secondName;
    private String patronymic;
    private Date birthdate;
    private String faculty;
    private Integer homeroomTeacherId;

    public StudentDto() {}

    public StudentDto(String firstName, String secondName, String patronymic, Date birthdate, String faculty) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.faculty = faculty;
    }

    public StudentDto(String firstName, String secondName, String patronymic, Date birthdate, String faculty, Integer homeroomTeacherId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.faculty = faculty;
        this.homeroomTeacherId = homeroomTeacherId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public String getFaculty() {
        return faculty;
    }

    public Integer getHomeroomTeacherId() {
        return homeroomTeacherId;
    }

    public void setHomeroomTeacherId(Integer homeroomTeacherId) {
        this.homeroomTeacherId = homeroomTeacherId;
    }
}
