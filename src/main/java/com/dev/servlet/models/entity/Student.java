package com.dev.servlet.models.entity;

import java.util.Date;

public class Student {
    private Integer id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private Date birthdate;
    private String faculty;
    private Integer homeroomTeacherId;

    public Student() {

    }

    public Student(String firstName, String secondName, String patronymic, Date birthdate, String faculty) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.faculty = faculty;
    }

    public Student(Integer id, String firstName, String secondName, String patronymic, Date birthdate, String faculty) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.faculty = faculty;
    }

    public Student(String firstName, String secondName, String patronymic, Date birthdate, String faculty, Integer homeroomTeacherId) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.birthdate = birthdate;
        this.faculty = faculty;
        this.homeroomTeacherId = homeroomTeacherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Integer getHomeroomTeacherId() {
        return homeroomTeacherId;
    }

    public void setHomeroomTeacherId(Integer homeroomTeacherId) {
        this.homeroomTeacherId = homeroomTeacherId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthdate=" + birthdate +
                ", faculty='" + faculty + '\'' +
                ", homeroomTeacherId=" + homeroomTeacherId +
                '}';
    }

}
