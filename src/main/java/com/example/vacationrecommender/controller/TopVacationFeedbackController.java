package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.dto.TopVacationFeedbackDTO;
import com.example.vacationrecommender.entity.TopVacationFeedback;
import com.example.vacationrecommender.repository.TopVacationFeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/top-feedback")
public class TopVacationFeedbackController {

    private final TopVacationFeedbackRepository repository;

    @Autowired
    public TopVacationFeedbackController(TopVacationFeedbackRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public ResponseEntity<?> saveFeedback(@RequestBody TopVacationFeedbackDTO dto) {
        System.out.println("Primit feedback: " + dto);  // Log simplu
        TopVacationFeedback feedback = new TopVacationFeedback();
        feedback.setNumePachet(dto.getNumePachet());
        feedback.setOrasStart(dto.getOrasStart());
        feedback.setComentariu(dto.getComentariu());
        feedback.setRating(dto.getRating());
        feedback.setUsername(dto.getUsername());
        feedback.setCreatedAt(LocalDateTime.now());
        repository.save(feedback);
        return ResponseEntity.ok("Feedback salvat!");
    }

    @GetMapping
    public List<TopVacationFeedback> getAll() {
        return repository.findAll();
    }

    @GetMapping("/{numePachet}/{orasStart}")
    public List<TopVacationFeedback> getByVacanta(@PathVariable String numePachet,
                                                  @PathVariable String orasStart) {
        return repository.findByNumePachetAndOrasStart(numePachet, orasStart);
    }



    @GetMapping("/sum/{numePachet}/{orasStart}")
    public ResponseEntity<Map<String, Object>> getRatingSiNrComentarii(
            @PathVariable String numePachet,
            @PathVariable String orasStart) {

        List<TopVacationFeedback> feedbackList = repository.findByNumePachetAndOrasStart(numePachet, orasStart);

        if (feedbackList.isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("medie", 0);
            response.put("count", 0);
            return ResponseEntity.ok(response);
        }

        double medie = feedbackList.stream().mapToInt(TopVacationFeedback::getRating).average().orElse(0.0);
        int count = feedbackList.size();

        Map<String, Object> response = new HashMap<>();
        response.put("medie", Math.round(medie * 10.0) / 10.0); // rotunjire
        response.put("count", count);

        return ResponseEntity.ok(response);
    }




}

