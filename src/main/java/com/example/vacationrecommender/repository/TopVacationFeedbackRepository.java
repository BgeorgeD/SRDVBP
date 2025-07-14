package com.example.vacationrecommender.repository;

import com.example.vacationrecommender.entity.TopVacationFeedback;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopVacationFeedbackRepository extends JpaRepository<TopVacationFeedback, Long> {
    List<TopVacationFeedback> findByNumePachetAndOrasStart(String numePachet, String orasStart);
}

