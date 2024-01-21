package ru.stileyn.taxi.controller.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.stileyn.taxi.lib.Injector;
import ru.stileyn.taxi.service.DriverService;

public class DeleteDriverController extends HttpServlet {
    private static final int ID_POSITION = 1;
    private static final Injector injector = Injector.getInstance("ru/stileyn/taxi");
    private DriverService driverService;

    @Override
    public void init() throws ServletException {
        driverService = (DriverService) injector.getInstance(DriverService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getPathInfo().split("/")[ID_POSITION];
        driverService.delete(Long.valueOf(id));
        resp.sendRedirect(req.getContextPath() + "/drivers");
    }
}
