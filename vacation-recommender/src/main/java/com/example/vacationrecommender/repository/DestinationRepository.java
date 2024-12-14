package com.example.vacationrecommender.repository;

import com.example.vacationrecommender.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
