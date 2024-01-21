package ru.stileyn.taxi.service;

import java.util.Optional;
import ru.stileyn.taxi.model.Driver;

public interface DriverService extends GenericService<Driver> {
    Optional<Driver> findByLogin(String login);
}
