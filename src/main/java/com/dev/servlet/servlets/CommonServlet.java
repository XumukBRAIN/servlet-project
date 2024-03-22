package com.dev.servlet.servlets;

import com.dev.servlet.configs.InstanceConfiguration;
import com.dev.servlet.enums.Specialization;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

import static com.dev.servlet.utils.Constant.CharacterEncodingConstants.UTF_8;
import static com.dev.servlet.utils.Constant.ContentTypeConstants.APPLICATION_JSON;
import static com.dev.servlet.utils.Constant.ContentTypeConstants.TEXT_PLAIN;
import static com.dev.servlet.utils.Constant.RequestParameterConstants.ACTION;

@WebServlet(name = "CommonServlet", urlPatterns = "/common")
public class CommonServlet extends HttpServlet {

    private final ObjectMapper objectMapper = InstanceConfiguration.getObjectMapperInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getHeader(ACTION);
        if (action.equals("specializations")) {
            try {
                resp.setCharacterEncoding(UTF_8);
                resp.setContentType(APPLICATION_JSON);
                resp.getWriter().write(objectMapper.writeValueAsString(
                        Arrays.stream(Specialization.values()).map(Specialization::getTitle).toArray())
                );
            } catch (IOException e) {
                throw new RuntimeException("Ошибка в ходе выполнения метода doGet#specializations в классе CommonServlet: " + e);
            }
        }
        try {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.setContentType(TEXT_PLAIN);
            resp.getWriter().write("Неизвестная doGet команда для получения общих данных");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}