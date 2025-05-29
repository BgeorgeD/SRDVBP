package com.example.vacationrecommender.controller;

import com.example.vacationrecommender.dto.AttractionDto;
import com.example.vacationrecommender.service.OpenTripMapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attractions")
public class OpenTripMapController {

    private final OpenTripMapService service;

    public OpenTripMapController(OpenTripMapService service) {
        this.service = service;
    }

    // ✅ Atracții pe baza numelui orașului
    @GetMapping
    public ResponseEntity<List<AttractionDto>> getAttractionsByCity(@RequestParam String city) {
        List<AttractionDto> results = service.getAttractionsByCity(city);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/details")
    public ResponseEntity<Map<String, Object>> getAttractionDetails(@RequestParam String xid) {
        Map<String, Object> details = service.getAttractionDetailsByXid(xid);
        return ResponseEntity.ok(details);
    }

}
