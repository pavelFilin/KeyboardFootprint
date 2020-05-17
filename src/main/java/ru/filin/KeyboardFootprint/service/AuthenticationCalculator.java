package ru.filin.KeyboardFootprint.service;

import ru.filin.KeyboardFootprint.entities.AuthenticationResult;
import ru.filin.KeyboardFootprint.entities.SimpleData;
import ru.filin.KeyboardFootprint.entities.User;

import java.util.List;

public interface AuthenticationCalculator {
    AuthenticationResult calculate(User user, SimpleData data, List<SimpleData> authorisationAttempts);
}
