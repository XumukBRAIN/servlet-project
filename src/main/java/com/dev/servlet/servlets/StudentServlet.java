package com.dev.servlet.servlets;

import com.dev.servlet.configs.InstanceConfiguration;
import com.dev.servlet.models.dto.StudentDto;
import com.dev.servlet.services.StudentService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.dev.servlet.utils.Constant.CharacterEncodingConstants.UTF_8;
import static com.dev.servlet.utils.Constant.CommonConstants.ID;
import static com.dev.servlet.utils.Constant.ContentTypeConstants.APPLICATION_JSON;
import static com.dev.servlet.utils.Constant.ContentTypeConstants.TEXT_PLAIN;
import static com.dev.servlet.utils.Constant.RequestParameterConstants.ACTION;

@WebServlet(name = "StudentServlet", urlPatterns = "/student")
public class StudentServlet extends HttpServlet {

    private final StudentService studentService = InstanceConfiguration.getStudentServiceInstance();
    private final ObjectMapper objectMapper = InstanceConfiguration.getObjectMapperInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getHeader(ACTION);
        switch (action) {
            case "getById":
                try {
                    Integer id = Integer.parseInt(req.getParameter(ID));
                    StudentDto studentDto = studentService.getById(id);
                    resp.setCharacterEncoding(UTF_8);
                    resp.setContentType(APPLICATION_JSON);
                    resp.getWriter().write(objectMapper.writeValueAsString(studentDto));
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка в ходе выполнения метода doGet#getById в классе StudentServlet: " + e);
                }
                break;
            case "getAll":
                try {
                    List<StudentDto> studentDtos = studentService.getAll();
                    resp.setCharacterEncoding(UTF_8);
                    resp.setContentType(APPLICATION_JSON);
                    resp.getWriter().write(objectMapper.writeValueAsString(studentDtos));
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка в ходе выполнения метода doGet#getAll в классе StudentServlet: " + e);
                }
                break;
            default:
                try {
                    resp.setCharacterEncoding(UTF_8);
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.setContentType(TEXT_PLAIN);
                    resp.getWriter().write("Неизвестная doGet команда для получения данных об учениках");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            StudentDto dto = objectMapper.readValue(req.getReader(), StudentDto.class);
            studentService.save(dto);
            resp.setCharacterEncoding(UTF_8);
            resp.setContentType(TEXT_PLAIN);
            resp.getWriter().write("Студент успешно добавлен");
        } catch (JsonParseException e) {
            throw new RuntimeException("Ошибка в ходе конвертации метода doPost в классе StudentServlet: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в ходе выполнения метода doPost в классе StudentServlet: " + e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer id = Integer.parseInt(req.getParameter(ID));
            StudentDto dto = objectMapper.readValue(req.getReader(), StudentDto.class);
            studentService.update(id, dto);
            resp.setCharacterEncoding(UTF_8);
            resp.setContentType(TEXT_PLAIN);
            resp.getWriter().write("Студент успешно обновлен");
        } catch (JsonParseException e) {
            throw new RuntimeException("Ошибка в ходе конвертации метода doPut в классе StudentServlet: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в ходе выполнения метода doPut в классе StudentServlet: " + e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer id = Integer.parseInt(req.getParameter(ID));
            studentService.delete(id);
            resp.setCharacterEncoding(UTF_8);
            resp.setContentType(TEXT_PLAIN);
            resp.getWriter().write("Студент успешно удален");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в ходе выполнения метода doDelete в классе StudentServlet: " + e);
        }
    }
}