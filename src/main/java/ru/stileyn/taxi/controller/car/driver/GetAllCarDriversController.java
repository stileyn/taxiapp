package ru.stileyn.taxi.controller.car.driver;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.stileyn.taxi.lib.Injector;
import ru.stileyn.taxi.model.Car;
import ru.stileyn.taxi.model.Driver;
import ru.stileyn.taxi.model.Manufacturer;
import ru.stileyn.taxi.service.CarService;
import ru.stileyn.taxi.service.DriverService;

public class GetAllCarDriversController extends HttpServlet {
    private static final int ID_POSITION = 1;
    private static final Injector injector = Injector.getInstance("ru/stileyn/taxi");
    private List<Driver> drivers;
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
        String id = req.getPathInfo().split("/")[ID_POSITION];
        Car car = carService.get(Long.valueOf(id));
        String model = car.getModel();
        drivers = car.getDrivers();
        req.setAttribute("id", id);
        req.setAttribute("model", model);
        Manufacturer manufacturer = car.getManufacturer();
        req.setAttribute("manufacturer", manufacturer);
        req.setAttribute("drivers", drivers);
        List<Driver> driversRemained = driverService.getAll()
                .stream()
                .filter(d -> !drivers.contains(d))
                .collect(Collectors.toList());
        req.setAttribute("drivers_remained", driversRemained);
        req.getRequestDispatcher("/WEB-INF/views/car/drivers.jsp").forward(req, resp);
    }
}
