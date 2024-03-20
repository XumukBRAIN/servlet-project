package com.dev.servlet;

import com.dev.servlet.models.entity.HomeroomTeacher;
import com.dev.servlet.repositories.HomeroomTeacherRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

@Testcontainers
@Tag("DockerRequired")
public class StudentDaoTest {
    private static final String INIT_SQL = "sql/schema.sql";
    public static HomeroomTeacherRepository homeroomTeacherRepository;

    @Container
    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres:15-alpine")
            .withInitScript(INIT_SQL);

    @BeforeAll
    static void beforeAll() {
        container.start();
    }

    @AfterAll
    static void afterAll() {
        container.stop();
    }

    @Test
    void test() {
        homeroomTeacherRepository = new HomeroomTeacherRepository(container.getJdbcUrl(), container.getUsername(), container.getPassword());
        List<HomeroomTeacher> all = homeroomTeacherRepository.getAll();
        System.out.println(all);
    }
}
