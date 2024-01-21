package ru.stileyn.taxi.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.stileyn.taxi.exception.AuthenticationException;
import ru.stileyn.taxi.lib.Injector;
import ru.stileyn.taxi.model.Driver;
import ru.stileyn.taxi.service.AuthenticationService;

public class LoginController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("ru/stileyn/taxi");
    private AuthenticationService authenticationService;

    @Override
    public void init() throws ServletException {
        authenticationService =
                (AuthenticationService) injector.getInstance(AuthenticationService.class);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        try {
            Driver driver = authenticationService.login(login, password);
            request.getSession().setAttribute("driver_id", driver.getId());
            response.sendRedirect(request.getContextPath() + "/drivers/cars");
        } catch (AuthenticationException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
        }
    }
}
