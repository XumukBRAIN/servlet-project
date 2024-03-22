package com.dev.servlet.utils;

import com.dev.servlet.models.entity.HomeroomTeacher;
import com.dev.servlet.models.entity.Student;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class QueryBuilder {
    private final StringBuilder queryBuilder;
    private final List<Object> queryParams;

    private QueryBuilder(Builder builder) {
        queryBuilder = builder.queryBuilder;
        queryParams = builder.queryParams;
    }

    public StringBuilder getQueryBuilder() {
        return queryBuilder;
    }

    public List<Object> getQueryParams() {
        return queryParams;
    }

    /**
     * Построение запроса для обновления ученика
     *
     * @param id Идентификатор ученика
     * @param student Данные ученика
     * @return QueryBuilder
     */
    public static QueryBuilder buildUpdateStudentQuery(Integer id, Student student) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE student SET ");
        List<Object> queryParams = new ArrayList<>();

        String firstName = student.getFirstName();
        if (firstName != null) {
            queryBuilder.append("first_name = ?, ");
            queryParams.add(firstName);
        }

        String secondName = student.getSecondName();
        if (secondName != null) {
            queryBuilder.append("second_name = ?, ");
            queryParams.add(secondName);
        }

        String patronymic = student.getPatronymic();
        if (patronymic != null) {
            queryBuilder.append("patronymic = ?, ");
            queryParams.add(patronymic);
        }

        Date birthdate = (Date) student.getBirthdate();
        if (birthdate != null) {
            queryBuilder.append("birthdate = ?, ");
            queryParams.add(birthdate);
        }

        String faculty = student.getFaculty();
        if (faculty != null) {
            queryBuilder.append("faculty = ?, ");
            queryParams.add(faculty);
        }

        Integer homeroomTeacherId = student.getHomeroomTeacherId();
        if (homeroomTeacherId!= null) {
            queryBuilder.append("homeroom_teacher_id = ?, ");
            queryParams.add(homeroomTeacherId);
        }

        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());

        queryBuilder.append(" WHERE id = ?");
        queryParams.add(id);

        return QueryBuilder.newBuilder()
                .withQueryBuilder(queryBuilder)
                .withQueryParams(queryParams)
                .build();
    }

    /**
     * Построение запроса для обновления классного руководителя
     *
     * @param id Идентификатор классного руководителя
     * @param homeroomTeacher Данные классного руководителя
     * @return QueryBuilder
     */
    public static QueryBuilder buildUpdateHomeroomTeacherQuery(Integer id, HomeroomTeacher homeroomTeacher) {
        StringBuilder queryBuilder = new StringBuilder("UPDATE homeroom_teacher SET ");
        List<Object> queryParams = new ArrayList<>();

        String firstName = homeroomTeacher.getFirstName();
        if (firstName != null) {
            queryBuilder.append("first_name = ?, ");
            queryParams.add(firstName);
        }

        String secondName = homeroomTeacher.getSecondName();
        if (secondName != null) {
            queryBuilder.append("second_name = ?, ");
            queryParams.add(secondName);
        }

        String patronymic = homeroomTeacher.getPatronymic();
        if (patronymic != null) {
            queryBuilder.append("patronymic = ?, ");
            queryParams.add(patronymic);
        }

        String specialization = homeroomTeacher.getSpecialization();
        if (specialization!= null) {
            queryBuilder.append("specialization = ?, ");
            queryParams.add(specialization);
        }

        queryBuilder.delete(queryBuilder.length() - 2, queryBuilder.length());

        queryBuilder.append(" WHERE id = ?");
        queryParams.add(id);

        return QueryBuilder.newBuilder()
              .withQueryBuilder(queryBuilder)
              .withQueryParams(queryParams)
              .build();
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder {
        private StringBuilder queryBuilder;
        private List<Object> queryParams;


        public Builder withQueryBuilder(StringBuilder val) {
            queryBuilder = val;
            return this;
        }

        public Builder withQueryParams(List<Object> val) {
            queryParams = val;
            return this;
        }

        public QueryBuilder build() {
            return new QueryBuilder(this);
        }
    }
}
