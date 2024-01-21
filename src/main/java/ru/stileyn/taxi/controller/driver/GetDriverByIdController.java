package ru.stileyn.taxi.controller.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.stileyn.taxi.lib.Injector;
import ru.stileyn.taxi.model.Driver;
import ru.stileyn.taxi.service.DriverService;

public class GetDriverByIdController extends HttpServlet {
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
        Driver driver = driverService.get(Long.valueOf(id));
        req.setAttribute("id", id);
        req.setAttribute("name", driver.getName());
        req.setAttribute("license_number", driver.getLicenseNumber());
        req.setAttribute("login", driver.getLogin());
        req.getRequestDispatcher("/WEB-INF/views/driver/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getPathInfo().split("/")[ID_POSITION];
        Driver updatedDriver = new Driver();
        updatedDriver.setId(Long.valueOf(id));
        String name = req.getParameter("name");
        updatedDriver.setName(name);
        String licenseNumber = req.getParameter("license_number");
        updatedDriver.setLicenseNumber(licenseNumber);
        Driver driver = driverService.update(updatedDriver);
        req.setAttribute("id", id);
        req.setAttribute("name", driver.getName());
        req.setAttribute("license_number", driver.getLicenseNumber());
        req.setAttribute("login", driver.getLogin());
        req.setAttribute("title", "Driver ("
                + name + "/" + licenseNumber
                + ") has been successfully updated");
        req.getRequestDispatcher("/WEB-INF/views/driver/edit.jsp").forward(req, resp);
    }
}
