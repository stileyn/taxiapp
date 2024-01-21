package ru.stileyn.taxi.dao;

import java.util.Optional;

import ru.stileyn.taxi.model.Driver;

public interface DriverDao extends GenericDao<Driver> {
    Optional<Driver> findByLogin(String login);
}
