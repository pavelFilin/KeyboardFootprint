package ru.filin.KeyboardFootprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.filin.KeyboardFootprint.entities.User;

public interface UserDetailsRepository extends JpaRepository<User, String> {
}
