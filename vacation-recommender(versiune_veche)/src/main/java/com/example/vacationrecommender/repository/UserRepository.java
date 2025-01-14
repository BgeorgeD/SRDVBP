package com.example.vacationrecommender.repository;

import com.example.vacationrecommender.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Metodă pentru găsirea unui utilizator după username
    Optional<User> findByUsername(String username);
}
