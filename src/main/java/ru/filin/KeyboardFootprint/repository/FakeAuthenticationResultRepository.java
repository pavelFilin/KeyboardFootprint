package ru.filin.KeyboardFootprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.filin.KeyboardFootprint.entities.FakeAuthenticationResult;

public interface FakeAuthenticationResultRepository extends JpaRepository<FakeAuthenticationResult, Long> {
}
