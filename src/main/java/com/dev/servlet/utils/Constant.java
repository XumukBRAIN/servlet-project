package com.dev.servlet.utils;

public interface Constant {
    interface CommonConstants {
        String ID = "id";
        String FIRSTNAME = "firstname";
        String SECONDNAME = "secondname";
        String PATRONYMIC = "patronymic";
        String USERNAME = "username";
        String PASSWORD = "password";
    }

    interface StudentConstants {
        String STUDENT_FIRSTNAME = "student_firstname";
        String STUDENT_SECONDNAME = "student_secondname";
        String STUDENT_PATRONYMIC = "student_patronymic";
        String FACULTY = "faculty";
        String BIRTHDATE = "birthdate";
    }

    interface HomeroomTeacherConstants {
        String SPECIALIZATION = "specialization";
    }

    interface ContentTypeConstants {
        String APPLICATION_JSON = "application/json";
        String TEXT_PLAIN = "text/plain";
    }

    interface CharacterEncodingConstants {
        String UTF_8 = "UTF-8";
    }

    interface RequestParameterConstants {
        String ACTION = "action";
    }
    
    interface JDBCFieldsConstants {
        String URL = "url";
        String DRIVER = "driver-class-name";
    }

    interface SecurityActionTypeConstants {
        String SECURITY_ACTION_TYPE = "security_action_type";
        String LOGIN = "login";
        String LOGOUT = "logout";
        String REGISTER = "register";
    }
}