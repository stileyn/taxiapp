package ru.stileyn.taxi.controller.car.driver;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.stileyn.taxi.lib.Injector;
import ru.stileyn.taxi.model.Car;
import ru.stileyn.taxi.model.Driver;
import ru.stileyn.taxi.service.CarService;
import ru.stileyn.taxi.service.DriverService;

public class AddDriverToCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("ru/stileyn/taxi");
    private CarService carService;
    private DriverService driverService;

    @Override
    public void init() throws ServletException {
        carService = (CarService) injector.getInstance(CarService.class);
        driverService = (DriverService) injector.getInstance(DriverService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/drivers/add.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        Long driverId = Long.valueOf(req.getParameter("driver_id"));
        Long carId = Long.valueOf(req.getParameter("car_id"));
        Car car = carService.get(carId);
        Driver driver = driverService.get(driverId);
        carService.addDriverToCar(driver, car);
        resp.sendRedirect(req.getContextPath() + "/cars/drivers/" + carId);
    }
}
