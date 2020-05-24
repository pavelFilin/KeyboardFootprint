package ru.filin.KeyboardFootprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.filin.KeyboardFootprint.entities.SimpleData;
import ru.filin.KeyboardFootprint.entities.User;

import java.util.List;

public interface SimpleDateRepository extends JpaRepository<SimpleData, Long> {
    List<SimpleData> findByUser(User u);
}
