package ru.filin.KeyboardFootprint.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.filin.KeyboardFootprint.entities.DataEntry;

public interface DataEntryRepository extends JpaRepository<DataEntry, Long> {
}
