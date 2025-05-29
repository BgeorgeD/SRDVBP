package com.example.vacationrecommender.service;

import com.example.vacationrecommender.dto.AttractionDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class OpenTripMapService {

    @Value("${opentripmap.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<AttractionDto> getAttractionsByCity(String cityName) {
        List<AttractionDto> result = new ArrayList<>();

        try {
            // 1. Coordonate
            String geoUrl = String.format("https://api.opentripmap.com/0.1/en/places/geoname?name=%s&apikey=%s", cityName, apiKey);
            JsonNode geoJson = objectMapper.readTree(restTemplate.getForObject(geoUrl, String.class));

            double lat = geoJson.get("lat").asDouble();
            double lon = geoJson.get("lon").asDouble();

            // 2. Atracții
            String radiusUrl = String.format("https://api.opentripmap.com/0.1/en/places/radius?radius=2000&lon=%s&lat=%s&limit=30&format=geojson&apikey=%s", lon, lat, apiKey);
            JsonNode root = objectMapper.readTree(restTemplate.getForObject(radiusUrl, String.class));
            JsonNode features = root.get("features");

            for (JsonNode feature : features) {
                String name = feature.at("/properties/name").asText();
                String kinds = feature.at("/properties/kinds").asText();
                double poiLat = feature.at("/geometry/coordinates/1").asDouble();
                double poiLon = feature.at("/geometry/coordinates/0").asDouble();

                if (!name.isBlank()) {
                    result.add(new AttractionDto(name, poiLat, poiLon, kinds));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Poți folosi un logger
        }

        return result;
    }
}
