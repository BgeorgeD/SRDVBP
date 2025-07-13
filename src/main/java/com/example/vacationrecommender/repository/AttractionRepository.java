package com.example.vacationrecommender.repository;


import com.example.vacationrecommender.entity.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    boolean existsByXid(String xid);
}

