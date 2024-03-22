package com.dev.servlet;

import com.dev.servlet.models.entity.HomeroomTeacher;
import com.dev.servlet.models.entity.Student;
import com.dev.servlet.repositories.HomeroomTeacherRepository;
import org.junit.jupiter.api.*;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.dev.servlet.utils.Constant.CommonConstants.PASSWORD;
import static com.dev.servlet.utils.Constant.CommonConstants.USERNAME;
import static com.dev.servlet.utils.Constant.JDBCFieldsConstants.DRIVER;
import static com.dev.servlet.utils.Constant.JDBCFieldsConstants.URL;

@Testcontainers
@Tag("DockerRequired")
public class HomeroomTeacherRepositoryTest {
    private static final String INIT_SQL = "sql/container_init_schema.sql";

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15-alpine")
            .withInitScript(INIT_SQL);

    private final Map<String, Object> dataSourceProperties = new HashMap<String, Object>() {{
        put(URL, container.getJdbcUrl());
        put(USERNAME, container.getUsername());
        put(PASSWORD, container.getPassword());
        put(DRIVER, container.getDriverClassName());
    }};

    public final HomeroomTeacherRepository homeroomTeacherRepository = new HomeroomTeacherRepository(dataSourceProperties);

    @BeforeAll
    static void beforeAll() {
        container.start();
    }

    @AfterAll
    static void afterAll() {
        container.stop();
    }

    @Test
    void testFindAll() {
        List<HomeroomTeacher> all = homeroomTeacherRepository.getAll();
        Assertions.assertEquals(all.size(), 1);
        HomeroomTeacher actual = all.get(0);
        Assertions.assertEquals(actual.getFirstName(), "Иван");
    }

    @Test
    void testFindByIdWithStudents() {
        HomeroomTeacher homeroomTeacher = homeroomTeacherRepository.getByIdWithStudents(1);
        Assertions.assertEquals(homeroomTeacher.getFirstName(), "Иван");
        Student student = homeroomTeacher.getStudents().get(0);
        Assertions.assertEquals(student.getFirstName(), "Петр");
    }
}
