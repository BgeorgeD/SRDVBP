package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.dto.RecommendationResult;
import com.example.vacationrecommender.dto.UserInput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
public class AiController {


    // Primește datele de la formularul JS și face request către Python
    @PostMapping("/api/ai/recommend")
    @ResponseBody
    public ResponseEntity<?> getRecommendationFromPython(@RequestBody UserInput input) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:5000/recommend"; // Flask server

        ResponseEntity<RecommendationResult[]> response = restTemplate.postForEntity(
                url,
                input,
                RecommendationResult[].class
        );

        return ResponseEntity.ok(response.getBody());
    }

}
