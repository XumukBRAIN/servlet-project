package com.dev.servlet.utils;

public class Constant {
    public interface CommonConstants {
        String ID = "id";
        String FIRSTNAME = "firstname";
        String SECONDNAME = "secondname";
        String PATRONYMIC = "patronymic";
    }

    public interface StudentConstants {
        String STUDENT_FIRSTNAME = "student_firstname";
        String STUDENT_SECONDNAME = "student_secondname";
        String STUDENT_PATRONYMIC = "student_patronymic";
        String FACULTY = "faculty";
        String BIRTHDATE = "birthdate";
        String HOMEROOM_TEACHER_ID = "homeroom_teacher_id";
    }

    public interface HomeroomTeacherConstants {
        String SPECIALIZATION = "specialization";
    }

    public interface ContentTypeConstants {
        String APPLICATION_JSON = "application/json";
        String TEXT_PLAIN = "text/plain";
        String APPLICATION_XML = "application/xml";
        String TEXT_XML = "text/xml";
        String TEXT_HTML = "text/html";
        String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";
        String MULTIPART_FORM_DATA = "multipart/form-data";
    }

    public interface CharacterEncodingConstants {
        String UTF_8 = "UTF-8";
    }

    public interface RequestParameterConstants {
        String ACTION = "action";
    }
}