package ru.stileyn.taxi.controller.manufacturer;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.stileyn.taxi.lib.Injector;
import ru.stileyn.taxi.service.ManufacturerService;

public class DeleteManufacturerController extends HttpServlet {
    private static final int ID_POSITION = 1;
    private static final Injector injector = Injector.getInstance("ru/stileyn/taxi");
    private ManufacturerService manufacturerService;

    @Override
    public void init() throws ServletException {
        manufacturerService = (ManufacturerService) injector.getInstance(ManufacturerService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getPathInfo().split("/")[ID_POSITION];
        manufacturerService.delete(Long.valueOf(id));
        resp.sendRedirect(req.getContextPath() + "/manufacturers");
    }
}
