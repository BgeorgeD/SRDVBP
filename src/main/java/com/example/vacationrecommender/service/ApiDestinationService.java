package com.example.vacationrecommender.service;

import com.example.vacationrecommender.dto.ApiResponse;
import com.example.vacationrecommender.dto.HotelDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiDestinationService {

    private final RestTemplate restTemplate;

    @Value("${travel.api.key}")
    private String travelToken;

    public ApiDestinationService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    public List<HotelDTO> getDestinationsByQuery(String query) {
        String url = "https://engine.hotellook.com/api/v2/lookup.json?query=" + query + "&token=" + travelToken;

        ResponseEntity<ApiResponse> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<ApiResponse>() {}
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody().getResults().getHotels();
        } else {
            return List.of();
        }
    }
}
