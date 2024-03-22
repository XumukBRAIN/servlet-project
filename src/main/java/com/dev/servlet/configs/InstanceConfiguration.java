package com.dev.servlet.configs;

import com.dev.servlet.converters.HomeroomTeacherConverter;
import com.dev.servlet.converters.StudentConverter;
import com.dev.servlet.repositories.HomeroomTeacherRepository;
import com.dev.servlet.repositories.SecurityRepository;
import com.dev.servlet.repositories.StudentRepository;
import com.dev.servlet.services.HomeroomTeacherService;
import com.dev.servlet.services.SecurityService;
import com.dev.servlet.services.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

//TODO: Переделать на Enum
public class InstanceConfiguration {
    private static ObjectMapper objectMapper = null;
    private static StudentRepository studentRepository = null;
    private static StudentService studentService = null;
    private static StudentConverter studentConverter = null;
    private static HomeroomTeacherRepository homeroomTeacherRepository = null;
    private static HomeroomTeacherConverter homeroomTeacherConverter = null;
    private static HomeroomTeacherService homeroomTeacherService = null;
    private static SecurityService securityService = null;
    private static SecurityRepository securityRepository = null;

    public static ObjectMapper getObjectMapperInstance() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }

        return objectMapper;
    }

    public static StudentRepository getStudentRepositoryInstance() {
        if (studentRepository == null) {
            studentRepository = new StudentRepository(DataSourceConfiguration.getDataSourceProperties());
        }

        return studentRepository;
    }

    public static StudentService getStudentServiceInstance() {
        if (studentService == null) {
            studentService = new StudentService();
        }

        return studentService;
    }

    public static StudentConverter getStudentConverterInstance() {
        if (studentConverter == null) {
            studentConverter = new StudentConverter();
        }

        return studentConverter;
    }

    public static HomeroomTeacherRepository getHomeroomTeacherRepositoryInstance() {
        if (homeroomTeacherRepository == null) {
            homeroomTeacherRepository = new HomeroomTeacherRepository(DataSourceConfiguration.getDataSourceProperties());
        }

        return homeroomTeacherRepository;
    }

    public static HomeroomTeacherService getHomeroomTeacherServiceInstance() {
        if (homeroomTeacherService == null) {
            homeroomTeacherService = new HomeroomTeacherService();
        }

        return homeroomTeacherService;
    }

    public static HomeroomTeacherConverter getHomeroomTeacherConverterInstance() {
        if (homeroomTeacherConverter == null) {
            homeroomTeacherConverter = new HomeroomTeacherConverter();
        }

        return homeroomTeacherConverter;
    }

    public static SecurityService getSecurityServiceInstance() {
        if (securityService == null) {
            securityService = new SecurityService();
        }

        return securityService;
    }

    public static SecurityRepository getSecurityRepositoryInstance() {
        if (securityRepository == null) {
            securityRepository = new SecurityRepository(DataSourceConfiguration.getDataSourceProperties());
        }

        return securityRepository;
    }
}
