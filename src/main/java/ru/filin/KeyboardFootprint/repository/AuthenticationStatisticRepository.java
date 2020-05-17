package ru.filin.KeyboardFootprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.filin.KeyboardFootprint.entities.AuthenticationResult;

public interface AuthenticationStatisticRepository extends JpaRepository<AuthenticationResult, Long> {
}
