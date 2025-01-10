package com.example.vacationrecommender.repository;

import com.example.vacationrecommender.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    List<Rating> findByDestinationId(Long destinationId);
}
