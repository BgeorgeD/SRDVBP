package com.example.vacationrecommender.service;

import com.example.vacationrecommender.dto.AttractionDto;
import com.example.vacationrecommender.dto.GlobDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GlobService {

    private final OpenTripMapService openTripMapService;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${opentripmap.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.opentripmap.com/0.1/en/places/radius";
    private static final String BASE_URL_BBOX = "https://api.opentripmap.com/0.1/en/places/bbox";

    public GlobService(OpenTripMapService openTripMapService) {
        this.openTripMapService = openTripMapService;
    }

    public List<GlobDTO> findNear(double lat, double lon, int radius) {
        try {
            String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                    .queryParam("apikey", apiKey)
                    .queryParam("lat", lat)
                    .queryParam("lon", lon)
                    .queryParam("radius", radius)
                    .queryParam("limit", 30)
                    .queryParam("format", "geojson")
                    .toUriString();

            String json = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(json);
            JsonNode features = root.get("features");

            if (features == null || !features.elements().hasNext()) {
                return List.of(); // nimic găsit
            }

            List<GlobDTO> result = new ArrayList<>();
            for (JsonNode feature : features) {
                String name = feature.at("/properties/name").asText();
                String kinds = feature.at("/properties/kinds").asText();
                String xid = feature.at("/properties/xid").asText();
                double poiLat = feature.at("/geometry/coordinates/1").asDouble();
                double poiLon = feature.at("/geometry/coordinates/0").asDouble();

                result.add(new GlobDTO(
                        xid,
                        name,
                        kinds,
                        "https://placehold.co/400x250",
                        0.0,
                        poiLat,
                        poiLon
                ));
            }

            return result;

        } catch (Exception e) {
            e.printStackTrace();  // poți înlocui cu logger.warn(...)
            return List.of();
        }
    }

    public List<GlobDTO> findByBoundingBox(double latMin, double latMax, double lonMin, double lonMax) {
        List<AttractionDto> attractions = openTripMapService.getAttractionsByBbox(lonMin, latMin, lonMax, latMax);

        if (attractions == null || attractions.isEmpty()) {
            return List.of();
        }

        return attractions.stream()
                .map(a -> new GlobDTO(
                        a.getXid(),
                        a.getName(),
                        a.getKinds(),
                        "https://placehold.co/400x250",
                        0.0,
                        a.getLat(),
                        a.getLon()
                ))
                .toList();
    }

    public Map<String, Object> getDetailsByXid(String xid) {
        return openTripMapService.getAttractionDetailsByXid(xid);
    }

}
