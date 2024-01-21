package ru.stileyn.taxi.service;

import ru.stileyn.taxi.exception.AuthenticationException;
import ru.stileyn.taxi.model.Driver;

public interface AuthenticationService {
    Driver login(String login, String password) throws AuthenticationException;
}
