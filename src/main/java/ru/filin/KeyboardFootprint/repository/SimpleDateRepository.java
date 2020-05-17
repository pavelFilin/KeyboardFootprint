package ru.filin.KeyboardFootprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.filin.KeyboardFootprint.entities.SimpleData;

public interface SimpleDateRepository extends JpaRepository<SimpleData, Long> {
}
