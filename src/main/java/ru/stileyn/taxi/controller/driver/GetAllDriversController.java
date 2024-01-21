package ru.stileyn.taxi.controller.driver;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.stileyn.taxi.lib.Injector;
import ru.stileyn.taxi.model.Driver;
import ru.stileyn.taxi.service.DriverService;

public class GetAllDriversController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("ru/stileyn/taxi");
    private DriverService driverService;

    @Override
    public void init() throws ServletException {
        driverService = (DriverService) injector.getInstance(DriverService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Collection<Driver> allDrivers = driverService.getAll();
        req.setAttribute("drivers", allDrivers);
        req.getRequestDispatcher("/WEB-INF/views/driver/all.jsp").forward(req, resp);
    }
}
