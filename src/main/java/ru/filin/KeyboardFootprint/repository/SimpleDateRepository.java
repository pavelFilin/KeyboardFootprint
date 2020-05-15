package ru.filin.KeyboardFootprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.filin.KeyboardFootprint.entities.SimpleDate;

public interface SimpleDateRepository extends JpaRepository<SimpleDate, Long> {
}
