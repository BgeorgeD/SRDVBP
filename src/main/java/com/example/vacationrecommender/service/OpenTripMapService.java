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
import java.util.Map;

@Service
public class OpenTripMapService {

    @Value("${opentripmap.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public OpenTripMapService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
    }

    public List<AttractionDto> getAttractionsByCity(String cityName) {
        List<AttractionDto> result = new ArrayList<>();

        try {
            // 1. Obține coordonatele orașului
            String geoUrl = String.format("https://api.opentripmap.com/0.1/en/places/geoname?name=%s&apikey=%s", cityName, apiKey);
            JsonNode geoJson = objectMapper.readTree(restTemplate.getForObject(geoUrl, String.class));

            double lat = geoJson.get("lat").asDouble();
            double lon = geoJson.get("lon").asDouble();

            // 2. Obține atracțiile turistice
            String radiusUrl = String.format(
                    "https://api.opentripmap.com/0.1/en/places/radius?radius=2000&lon=%s&lat=%s&limit=30&format=geojson&kinds=accomodations,interesting_places,museums,architecture&apikey=%s",
                    lon, lat, apiKey
            );

            JsonNode root = objectMapper.readTree(restTemplate.getForObject(radiusUrl, String.class));
            JsonNode features = root.get("features");

            for (JsonNode feature : features) {
                String name = feature.at("/properties/name").asText();
                String kinds = feature.at("/properties/kinds").asText();
                String xid = feature.at("/properties/xid").asText();
                double poiLat = feature.at("/geometry/coordinates/1").asDouble();
                double poiLon = feature.at("/geometry/coordinates/0").asDouble();

                if (!name.isBlank()) {
                    result.add(new AttractionDto(name, poiLat, poiLon, kinds, xid));


                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // sau folosește un logger
        }

        return result;
    }

    public Map<String, Object> getAttractionDetailsByXid(String xid) {
        String url = String.format("https://api.opentripmap.com/0.1/en/places/xid/%s?apikey=%s", xid, apiKey);
        try {
            ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
            return response.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of(); // întoarce un map gol dacă nu merge
        }
    }


    public List<AttractionDto> getAttractionsByBbox(double lonMin, double latMin, double lonMax, double latMax) {
        List<AttractionDto> result = new ArrayList<>();
        try {
            String url = String.format("https://api.opentripmap.com/0.1/en/places/bbox?lon_min=%s&lat_min=%s&lon_max=%s&lat_max=%s&format=geojson&limit=50&apikey=%s",
                    lonMin, latMin, lonMax, latMax, apiKey);

            JsonNode root = objectMapper.readTree(restTemplate.getForObject(url, String.class));
            JsonNode features = root.get("features");

            for (JsonNode feature : features) {
                String name = feature.at("/properties/name").asText();
                String kinds = feature.at("/properties/kinds").asText();
                String xid = feature.at("/properties/xid").asText();
                double poiLat = feature.at("/geometry/coordinates/1").asDouble();
                double poiLon = feature.at("/geometry/coordinates/0").asDouble();

                if (!name.isBlank()) {
                    result.add(new AttractionDto(name, poiLat, poiLon, kinds, xid));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }



}
