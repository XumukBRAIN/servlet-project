package com.dev.servlet.repositories;

import com.dev.servlet.models.entity.Student;
import com.dev.servlet.utils.QueryBuilder;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.dev.servlet.utils.Constant.CommonConstants.*;
import static com.dev.servlet.utils.Constant.JDBCFieldsConstants.DRIVER;
import static com.dev.servlet.utils.Constant.JDBCFieldsConstants.URL;
import static com.dev.servlet.utils.Constant.StudentConstants.BIRTHDATE;
import static com.dev.servlet.utils.Constant.StudentConstants.FACULTY;

public class StudentRepository {

    private final DataSource dataSource;

    public StudentRepository(Map<String, Object> dataSourceProperties) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl((String) dataSourceProperties.get(URL));
        config.setUsername((String) dataSourceProperties.get(USERNAME));
        config.setPassword((String) dataSourceProperties.get(PASSWORD));
        config.setDriverClassName((String) dataSourceProperties.get(DRIVER));

        dataSource = new HikariDataSource(config);
    }

    private static final String SAVE_SCRIPT = "INSERT INTO student(first_name, second_name, patronymic, birthdate, faculty) VALUES (?, ?, ?, ?, ?)";
    private static final String GET_ALL_SCRIPT = "SELECT * FROM student";
    private static final String GET_BY_ID_SCRIPT = "SELECT * FROM student WHERE id = ?";
    private static final String DELETE_BY_ID_SCRIPT = "DELETE FROM student WHERE id = ?";

    public void save(Student student) {
        if (student == null) {
            throw new RuntimeException("Не переданы данные для создания ученика");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(SAVE_SCRIPT)
        ) {
            ps.setString(1, student.getFirstName());
            ps.setString(2, student.getSecondName());
            ps.setString(3, student.getPatronymic());
            ps.setDate(4, (Date) student.getBirthdate());
            ps.setString(5, student.getFaculty());

            ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Integer id, Student student) {
        if (id == null) {
            throw new RuntimeException("Не передан идентификатор ученика для обновления");
        }

        if (student == null) {
            throw new RuntimeException("Не переданы данные для обновления ученика");
        }

        // Построение запроса для обновления ученика
        QueryBuilder query = QueryBuilder.buildUpdateStudentQuery(id, student);
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

    public Student getById(Integer id) {
        if (id == null) {
            throw new RuntimeException("Не передан идентификатор ученика для поиска");
        }

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(GET_BY_ID_SCRIPT)
        ) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    Student student = new Student();
                    student.setId(resultSet.getInt(ID));
                    student.setFirstName(resultSet.getString(FIRSTNAME));
                    student.setSecondName(resultSet.getString(SECONDNAME));
                    student.setPatronymic(resultSet.getString(PATRONYMIC));
                    student.setBirthdate(resultSet.getTimestamp(BIRTHDATE));
                    student.setFaculty(resultSet.getString(FACULTY));
                    return student;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    public List<Student> getAll() {
        List<Student> students = new ArrayList<>();
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement ps = connection.prepareStatement(GET_ALL_SCRIPT);
                ResultSet rs = ps.executeQuery()
        ) {
            while (rs.next()) {
                Student student = new Student();
                student.setFirstName(rs.getString(FIRSTNAME));
                student.setSecondName(rs.getString(SECONDNAME));
                student.setPatronymic(rs.getString(PATRONYMIC));
                student.setBirthdate(rs.getTimestamp(BIRTHDATE));
                student.setFaculty(rs.getString(FACULTY));
                students.add(student);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return students;
    }

    public void delete(Integer id) {
        if (id == null) {
            throw new RuntimeException("Не передан идентификатор ученика для удаления");
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