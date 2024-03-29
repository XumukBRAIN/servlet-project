package com.dev.servlet.repositories;

import com.dev.servlet.models.entity.HomeroomTeacher;
import com.dev.servlet.models.entity.Student;
import com.dev.servlet.utils.QueryBuilder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.dev.servlet.utils.Constant.CommonConstants.*;
import static com.dev.servlet.utils.Constant.HomeroomTeacherConstants.SPECIALIZATION;
import static com.dev.servlet.utils.Constant.JDBCFieldsConstants.DRIVER;
import static com.dev.servlet.utils.Constant.JDBCFieldsConstants.URL;
import static com.dev.servlet.utils.Constant.StudentConstants.*;

public class HomeroomTeacherRepository {

    private final DataSource dataSource;

    public HomeroomTeacherRepository(Map<String, Object> dataSourceProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl((String) dataSourceProperties.get(URL));
        config.setUsername((String) dataSourceProperties.get(USERNAME));
        config.setPassword((String) dataSourceProperties.get(PASSWORD));
        config.setDriverClassName((String) dataSourceProperties.get(DRIVER));

        dataSource = new HikariDataSource(config);
    }

    private static final String SAVE_SCRIPT = "INSERT INTO homeroom_teacher(firstname, secondname, patronymic, specialization) VALUES (?, ?, ?, ?)";
    private static final String GET_ALL_SCRIPT = "SELECT * FROM homeroom_teacher";
    private static final String GET_BY_ID_SCRIPT = "SELECT * FROM homeroom_teacher WHERE id = ?";
    private static final String DELETE_BY_ID_SCRIPT = "DELETE FROM homeroom_teacher WHERE id = ?";
    private static final String GET_BY_ID_SCRIPT_WITH_STUDENTS = "SELECT h.firstname, h.secondname, h.patronymic, h.specialization, " +
            "s.firstname as student_firstname, s.secondname as student_secondname, s.patronymic as student_patronymic, s.birthdate, s.faculty " +
            "FROM homeroom_teacher h " +
            "LEFT JOIN student s ON h.id = s.id " +
            "WHERE h.id = ?";

    public void save(HomeroomTeacher homeroomTeacher) {
        if (homeroomTeacher == null) {
            throw new RuntimeException("Не переданы данные для создания классного руководителя");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SAVE_SCRIPT)
        ) {
            ps.setString(1, homeroomTeacher.getFirstName());
            ps.setString(2, homeroomTeacher.getSecondName());
            ps.setString(3, homeroomTeacher.getPatronymic());
            ps.setString(4, homeroomTeacher.getSpecialization());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Integer id, HomeroomTeacher homeroomTeacher) {
        if (id == null) {
            throw new RuntimeException("Не передан идентификатор классного руководителя для обновления");
        }

        if (homeroomTeacher == null) {
            throw new RuntimeException("Не переданы данные для обновления классного руководителя");
        }

        // Построение запроса для обновления классного руководителя
        QueryBuilder query = QueryBuilder.buildUpdateHomeroomTeacherQuery(id, homeroomTeacher);
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(query.getQueryBuilder().toString())
        ) {
            // Простановка параметров запроса, см. QueryBuild.buildUpdateStudentQuery
            for (int i = 0; i < query.getQueryParams().size(); i++) {
                ps.setObject(i + 1, query.getQueryParams().get(i));
            }

            ps.executeUpdate();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    public HomeroomTeacher getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("Не передан идентификатор классного руководителя для поиска");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(GET_BY_ID_SCRIPT)
        ) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    HomeroomTeacher homeroomTeacher = new HomeroomTeacher();
                    homeroomTeacher.setFirstName(resultSet.getString(FIRSTNAME));
                    homeroomTeacher.setSecondName(resultSet.getString(SECONDNAME));
                    homeroomTeacher.setPatronymic(resultSet.getString(PATRONYMIC));
                    homeroomTeacher.setSpecialization(resultSet.getString(SPECIALIZATION));
                    return homeroomTeacher;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public HomeroomTeacher getByIdWithStudents(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("Не передан идентификатор классного руководителя для поиска");
        }

        HomeroomTeacher homeroomTeacher = null;
        List<Student> students = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(GET_BY_ID_SCRIPT_WITH_STUDENTS)
        ) {
            ps.setInt(1, id);
            try (
                    ResultSet resultSet = ps.executeQuery()
            ) {
                while (resultSet.next()) {
                    Student student = new Student();
                    student.setFirstName(resultSet.getString(STUDENT_FIRSTNAME));
                    student.setSecondName(resultSet.getString(STUDENT_SECONDNAME));
                    student.setPatronymic(resultSet.getString(STUDENT_PATRONYMIC));
                    student.setBirthdate(resultSet.getDate(BIRTHDATE));
                    student.setFaculty(resultSet.getString(FACULTY));
                    students.add(student);

                    if (homeroomTeacher == null) {
                        homeroomTeacher = new HomeroomTeacher();
                        homeroomTeacher.setFirstName(resultSet.getString(FIRSTNAME));
                        homeroomTeacher.setSecondName(resultSet.getString(SECONDNAME));
                        homeroomTeacher.setPatronymic(resultSet.getString(PATRONYMIC));
                        homeroomTeacher.setSpecialization(resultSet.getString(SPECIALIZATION));
                    }
                }
            }

            if (homeroomTeacher != null) {
                homeroomTeacher.setStudents(students);
            }

            return homeroomTeacher;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<HomeroomTeacher> getAll() {
        List<HomeroomTeacher> homeroomTeachers = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(GET_ALL_SCRIPT);
                ResultSet resultSet = ps.executeQuery()
        ) {
            while (resultSet.next()) {
                HomeroomTeacher homeroomTeacher = new HomeroomTeacher();
                homeroomTeacher.setFirstName(resultSet.getString(FIRSTNAME));
                homeroomTeacher.setSecondName(resultSet.getString(SECONDNAME));
                homeroomTeacher.setPatronymic(resultSet.getString(PATRONYMIC));
                homeroomTeacher.setSpecialization(resultSet.getString(SPECIALIZATION));
                homeroomTeachers.add(homeroomTeacher);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return homeroomTeachers;
    }

    public void delete(Integer id) {
        if (id == null) {
            throw new RuntimeException("Не передан идентификатор классного руководителя для удаления");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID_SCRIPT)
        ) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}