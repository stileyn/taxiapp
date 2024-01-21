package ru.stileyn.taxi.service.impl;

import java.util.Optional;

import ru.stileyn.taxi.exception.AuthenticationException;
import ru.stileyn.taxi.dao.DriverDao;
import ru.stileyn.taxi.lib.Inject;
import ru.stileyn.taxi.lib.Service;
import ru.stileyn.taxi.model.Driver;
import ru.stileyn.taxi.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Inject
    private DriverDao driverDao;

    @Override
    public Driver login(String login, String password) throws AuthenticationException {
        Optional<Driver> driver = driverDao.findByLogin(login);
        if (driver.isEmpty() || !driver.get().getPassword().equals(password)) {
            throw new AuthenticationException("User or password was incorrect");
        }
        return driver.get();
    }
}
