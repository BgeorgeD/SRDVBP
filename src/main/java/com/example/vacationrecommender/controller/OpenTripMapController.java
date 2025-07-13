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
        List<AttractionDto> results = service.getAndSaveAttractionsByCity(city); // ✅ salvăm automat
        return ResponseEntity.ok(results);
    }


    @GetMapping("/details")
    public ResponseEntity<Map<String, Object>> getAttractionDetails(@RequestParam String xid) {
        Map<String, Object> details = service.getAttractionDetailsByXid(xid);
        return ResponseEntity.ok(details);
    }

    @GetMapping("/bbox")
    public ResponseEntity<List<AttractionDto>> getAttractionsByBbox(
            @RequestParam("lon_min") double lonMin,
            @RequestParam("lat_min") double latMin,
            @RequestParam("lon_max") double lonMax,
            @RequestParam("lat_max") double latMax) {

        List<AttractionDto> results = service.getAttractionsByBbox(lonMin, latMin, lonMax, latMax);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/save")
    public ResponseEntity<List<AttractionDto>> getAndSaveByCity(@RequestParam String city) {
        List<AttractionDto> results = service.getAndSaveAttractionsByCity(city);
        return ResponseEntity.ok(results);
    }











}
