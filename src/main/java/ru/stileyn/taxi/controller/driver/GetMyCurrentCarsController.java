package ru.stileyn.taxi.controller.driver;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.stileyn.taxi.lib.Injector;
import ru.stileyn.taxi.model.Car;
import ru.stileyn.taxi.service.CarService;
import ru.stileyn.taxi.service.DriverService;

public class GetMyCurrentCarsController extends HttpServlet {
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
        Long currentDriverId = (Long) req.getSession().getAttribute("driver_id");
        String name = driverService.get(currentDriverId).getName();
        req.setAttribute("name", name);
        String licenseNumber = driverService.get(currentDriverId).getLicenseNumber();
        req.setAttribute("license_number", licenseNumber);
        List<Car> carsOfCurrentCDriver = carService.getAllByDriver(currentDriverId);
        req.setAttribute("my_cars", carsOfCurrentCDriver);
        List<Car> carsRemained = carService.getAll()
                .stream()
                .filter(car -> !carsOfCurrentCDriver.contains(car))
                .collect(Collectors.toList());
        req.setAttribute("cars_remained", carsRemained);
        req.getRequestDispatcher("/WEB-INF/views/driver/cars/my.jsp").forward(req, resp);
    }
}
