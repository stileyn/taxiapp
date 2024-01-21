package ru.stileyn.taxi.dao;

import java.util.List;

import ru.stileyn.taxi.model.Car;

public interface CarDao extends GenericDao<Car> {
    List<Car> getAllByDriver(Long driverId);
}
