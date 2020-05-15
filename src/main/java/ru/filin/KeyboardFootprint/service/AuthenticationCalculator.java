package ru.filin.KeyboardFootprint.service;

import ru.filin.KeyboardFootprint.entities.SimpleDate;
import ru.filin.KeyboardFootprint.entities.User;

public interface AuthenticationCalculator {
    int calculate(User user, SimpleDate data);
}
