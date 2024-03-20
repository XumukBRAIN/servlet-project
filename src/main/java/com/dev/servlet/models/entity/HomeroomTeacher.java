package com.dev.servlet.models.entity;

import java.util.List;

public class HomeroomTeacher {
    private Integer id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private String specialization;
    private List<Student> students;

    public HomeroomTeacher() {}

    public HomeroomTeacher(Integer id, String firstName, String secondName, String patronymic, String specialization, List<Student> students) {
        this.id = id;
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.specialization = specialization;
        this.students = students;
    }

    public HomeroomTeacher(String firstName, String secondName, String patronymic, String specialization, List<Student> students) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.specialization = specialization;
        this.students = students;
    }

    public HomeroomTeacher(String firstName, String secondName, String patronymic, String specialization) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.patronymic = patronymic;
        this.specialization = specialization;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "HomeroomTeacher{" +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}
