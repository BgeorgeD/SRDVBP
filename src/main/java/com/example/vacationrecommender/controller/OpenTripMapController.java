package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.dto.AttractionDto;
import com.example.vacationrecommender.service.OpenTripMapService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/*@RestController
@RequestMapping("/api/attractii")
public class OpenTripMapController {

    @Autowired
    private OpenTripMapService openTripMapService;

    @GetMapping
    public List<String> getAttractii(@RequestParam String oras) {
        return openTripMapService.getAttractions(oras);
    }
}*/

@RestController
@RequestMapping("/api/attractions")
public class OpenTripMapController {

    private final OpenTripMapService service;

    public OpenTripMapController(OpenTripMapService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<AttractionDto>> getAttractionsByCity(@RequestParam String city) {
        List<AttractionDto> results = service.getAttractionsByCity(city);
        return ResponseEntity.ok(results);
    }
}



