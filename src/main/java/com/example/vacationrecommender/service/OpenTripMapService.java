package com.example.vacationrecommender.service;

import com.example.vacationrecommender.dto.AttractionDto;
import com.example.vacationrecommender.entity.Attraction;
import com.example.vacationrecommender.entity.Destination;
import com.example.vacationrecommender.repository.AttractionRepository;
import com.example.vacationrecommender.repository.DestinationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DestinationRepository destinationRepository;

    @Autowired
    private AttractionRepository attractionRepository;


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
            String url = String.format(
                    "https://api.opentripmap.com/0.1/en/places/bbox?lon_min=%s&lat_min=%s&lon_max=%s&lat_max=%s&format=geojson&limit=100&apikey=%s",
                    lonMin, latMin, lonMax, latMax, apiKey
            );

            System.out.println("🔍 API BBOX URL: " + url);  // DEBUG

            JsonNode root = objectMapper.readTree(restTemplate.getForObject(url, String.class));
            JsonNode features = root.get("features");

            if (features == null || !features.isArray()) {
                System.out.println("❌ Nicio secțiune 'features' în răspuns.");
                return result;
            }

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
            System.out.println("❗ Eroare în getAttractionsByBbox: " + e.getMessage());
        }

        return result;
    }


    public void fetchAndSaveAttractionsForDestination(Long destinationId) {
        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new RuntimeException("Destinația nu există"));

        double lat = destination.getLatitude();
        double lon = destination.getLongitude();

        // Folosim metoda ta existentă (reuse)
        List<AttractionDto> dtos = getAttractionsByBbox(
                lon - 0.05, lat - 0.05, lon + 0.05, lat + 0.05
        );

        for (AttractionDto dto : dtos) {
            if (!attractionRepository.existsByXid(dto.getXid())) {
                Attraction attraction = new Attraction();
                attraction.setXid(dto.getXid());
                attraction.setName(dto.getName());
                attraction.setDescription(""); // Poți completa mai târziu cu getAttractionDetailsByXid()
                attraction.setDestination(destination);
                attractionRepository.save(attraction);
            }
        }
    }

    public List<AttractionDto> getAndSaveAttractionsByCity(String cityName) {
        List<AttractionDto> dtos = getAttractionsByCity(cityName);




        JsonNode geoJson = null;



        double lat = 0;
        double lon = 0;

        try {
            String geoUrl = String.format("https://api.opentripmap.com/0.1/en/places/geoname?name=%s&apikey=%s", cityName, apiKey);
            geoJson = objectMapper.readTree(restTemplate.getForObject(geoUrl, String.class));
            lat = geoJson.get("lat").asDouble();
            lon = geoJson.get("lon").asDouble();
        } catch (Exception e) {
            e.printStackTrace();
        }
        final double finalLat = lat;
        final double finalLon = lon;




        Destination destination = destinationRepository.findByCity(cityName)
                .orElseGet(() -> {
                    Destination newDest = new Destination();
                    newDest.setCity(cityName);
                    newDest.setName(cityName); // ← adaugă asta
                    newDest.setLatitude(finalLat);
                    newDest.setLongitude(finalLon);
                    return destinationRepository.save(newDest);
                });





        for (AttractionDto dto : dtos) {
            if (!attractionRepository.existsByXid(dto.getXid())) {
                Attraction attraction = new Attraction();
                attraction.setXid(dto.getXid());
                attraction.setName(dto.getName());
                attraction.setDescription(""); // se poate completa ulterior
                attraction.setDestination(destination);
                attractionRepository.save(attraction);
            }
        }

        return dtos;
    }






}
