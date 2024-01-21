package ru.stileyn.taxi.service.impl;

import java.util.List;
import ru.stileyn.taxi.dao.CarDao;
import ru.stileyn.taxi.lib.Inject;
import ru.stileyn.taxi.lib.Service;
import ru.stileyn.taxi.model.Car;
import ru.stileyn.taxi.model.Driver;
import ru.stileyn.taxi.service.CarService;

@Service
public class CarServiceImpl implements CarService {
    @Inject
    private CarDao carDao;

    @Override
    public void addDriverToCar(Driver driver, Car car) {
        car.getDrivers().add(driver);
        carDao.update(car);
    }

    @Override
    public void removeDriverFromCar(Driver driver, Car car) {
        car.getDrivers().remove(driver);
        carDao.update(car);
    }

    @Override
    public List<Car> getAllByDriver(Long driverId) {
        return carDao.getAllByDriver(driverId);
    }

    @Override
    public Car create(Car car) {
        return carDao.create(car);
    }

    @Override
    public Car get(Long id) {
        return carDao.get(id).get();
    }

    @Override
    public List<Car> getAll() {
        return carDao.getAll();
    }

    @Override
    public Car update(Car car) {
        return carDao.update(car);
    }

    @Override
    public boolean delete(Long id) {
        return carDao.delete(id);
    }
}
