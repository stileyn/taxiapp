package ru.stileyn.taxi.service;

import java.util.List;

import ru.stileyn.taxi.model.Driver;
import ru.stileyn.taxi.model.Car;

public interface CarService extends GenericService<Car> {
    void addDriverToCar(Driver driver, Car car);

    void removeDriverFromCar(Driver driver, Car car);

    List<Car> getAllByDriver(Long driverId);
}
