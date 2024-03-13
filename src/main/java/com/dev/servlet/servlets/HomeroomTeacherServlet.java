package com.dev.servlet.servlets;

import com.dev.servlet.configs.InstanceConfiguration;
import com.dev.servlet.models.dto.HomeroomTeacherDto;
import com.dev.servlet.services.HomeroomTeacherService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.dev.servlet.utils.Constant.CharacterEncodingConstants.UTF_8;
import static com.dev.servlet.utils.Constant.CommonConstants.ID;
import static com.dev.servlet.utils.Constant.ContentTypeConstants.TEXT_PLAIN;
import static com.dev.servlet.utils.Constant.RequestParameterConstants.ACTION;

@WebServlet(name = "HomeroomTeacherServlet", urlPatterns = "/homeroomTeacher")
public class HomeroomTeacherServlet extends HttpServlet {

    private final HomeroomTeacherService homeroomTeacherService = InstanceConfiguration.getHomeroomTeacherServiceInstance();
    private final ObjectMapper objectMapper = InstanceConfiguration.getObjectMapperInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getHeader(ACTION);
        switch (action) {
            case "getAll":
                try {
                    resp.setCharacterEncoding(UTF_8);
                    resp.setContentType(TEXT_PLAIN);
                    resp.getWriter().write(objectMapper.writeValueAsString(homeroomTeacherService.getAll()));
                } catch (JsonParseException e) {
                    throw new RuntimeException("Ошибка конвертации JSON в DTO в классе HomeroomTeacherServlet: " + e);
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка в ходе выполнения метода doGet в классе HomeroomTeacherServlet: " + e);
                }
                break;
            case "getById":
                try {
                    Integer id = Integer.parseInt(req.getParameter(ID));
                    HomeroomTeacherDto dto = homeroomTeacherService.getById(id);
                    resp.setCharacterEncoding(UTF_8);
                    resp.setContentType(TEXT_PLAIN);
                    resp.getWriter().write(objectMapper.writeValueAsString(dto));
                } catch (JsonParseException e) {
                    throw new RuntimeException("Ошибка конвертации JSON в DTO в классе HomeroomTeacherServlet: " + e);
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка в ходе выполнения метода doGet в классе HomeroomTeacherServlet: " + e);
                }
                break;
            case "getByIdWithStudents":
                try {
                    Integer id = Integer.parseInt(req.getParameter(ID));
                    HomeroomTeacherDto dto = homeroomTeacherService.getByIdWithStudents(id);
                    resp.setCharacterEncoding(UTF_8);
                    resp.setContentType(TEXT_PLAIN);
                    resp.getWriter().write(objectMapper.writeValueAsString(dto));
                } catch (JsonParseException e) {
                    throw new RuntimeException("Ошибка конвертации JSON в DTO в классе HomeroomTeacherServlet: " + e);
                } catch (IOException e) {
                    throw new RuntimeException("Ошибка в ходе выполнения метода doGet в классе HomeroomTeacherServlet: " + e);
                }
                break;
            default:
                try {
                    resp.setCharacterEncoding(UTF_8);
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.setContentType(TEXT_PLAIN);
                    resp.getWriter().write("Неизвестная doGet команда для получения данных о классных руководителях");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer id = Integer.parseInt(req.getParameter(ID));
            HomeroomTeacherDto dto = objectMapper.readValue(req.getReader(), HomeroomTeacherDto.class);
            homeroomTeacherService.update(id, dto);
            resp.setCharacterEncoding(UTF_8);
            resp.setContentType(TEXT_PLAIN);
            resp.getWriter().write("Классный руководитель успешно обновлен");
        } catch (JsonParseException e) {
            throw new RuntimeException("Ошибка конвертации JSON в DTO в классе HomeroomTeacherServlet: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в ходе выполнения метода doPut в классе HomeroomTeacherServlet: " + e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HomeroomTeacherDto dto = objectMapper.readValue(req.getReader(), HomeroomTeacherDto.class);
            homeroomTeacherService.save(dto);
            resp.setCharacterEncoding(UTF_8);
            resp.setContentType(TEXT_PLAIN);
            resp.getWriter().write("Классный руководитель успешно сохранен");
        } catch (JsonParseException e) {
            throw new RuntimeException("Ошибка конвертации JSON в DTO в классе HomeroomTeacherServlet: " + e);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в ходе выполнения метода doPost в классе HomeroomTeacherServlet: " + e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Integer id = Integer.parseInt(req.getParameter(ID));
            homeroomTeacherService.delete(id);
            resp.setCharacterEncoding(UTF_8);
            resp.setContentType(TEXT_PLAIN);
            resp.getWriter().write("Классный руководитель успешно удален");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка в ходе выполнения метода doDelete в классе HomeroomTeacherServlet: " + e);
        }
    }
}