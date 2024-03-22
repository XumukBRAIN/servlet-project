package com.dev.servlet.servlets;

import com.dev.servlet.configs.InstanceConfiguration;
import com.dev.servlet.security.UserInfo;
import com.dev.servlet.security.SecurityContextHolder;
import com.dev.servlet.services.SecurityService;
import com.dev.servlet.utils.Constant;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "CommonServlet", urlPatterns = "/security")
public class SecurityServlet extends HttpServlet {

    private final SecurityService securityService = InstanceConfiguration.getSecurityServiceInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String securityType = req.getHeader(Constant.SecurityActionTypeConstants.SECURITY_ACTION_TYPE);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        switch (securityType) {
            case Constant.SecurityActionTypeConstants.REGISTER:
                try {
                    UserInfo userInfo = UserInfo.newBuilder()
                            .withUsername(username)
                            .withPassword(password)
                            .build();
                    securityService.register(userInfo);
                    SecurityContextHolder.setUserInfo(userInfo);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                HttpSession session = req.getSession();
                session.setAttribute("username", username);
                session.setAttribute("password", password);
                break;
            case Constant.SecurityActionTypeConstants.LOGOUT:
                req.getSession().removeAttribute("username");
                req.getSession().removeAttribute("password");
                break;
        }
    }
}
