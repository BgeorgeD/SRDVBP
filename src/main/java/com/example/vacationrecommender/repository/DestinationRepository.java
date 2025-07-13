package com.example.vacationrecommender.repository;

import com.example.vacationrecommender.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Optional<Destination> findByCity(String city);  // <-- adaugă această linie
}
