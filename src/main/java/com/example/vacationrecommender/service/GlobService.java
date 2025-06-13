package com.example.vacationrecommender.service;

import com.example.vacationrecommender.dto.GlobDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class GlobService {


    @Value("${opentripmap.api.key}")
    private String apiKey;

    private static final String BASE_URL = "https://api.opentripmap.com/0.1/en/places/radius";

    private final RestTemplate restTemplate = new RestTemplate();

    public List<GlobDTO> findNear(double lat, double lon, int radius) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("apikey", apiKey)
                .queryParam("lat", lat)
                .queryParam("lon", lon)
                .queryParam("radius", radius)
                .queryParam("limit", 15)
                .queryParam("rate", 2)
                .toUriString();

        ResponseEntity<Map<String, Object>> resp = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {}
        );

        List<Map<String, Object>> features =
                (List<Map<String, Object>>) resp.getBody().get("features");

        return features.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private GlobDTO toDto(Map<String, Object> feature) {
        Map<String, Object> props = (Map<String, Object>) feature.get("properties");
        Map<String, Object> geom  = (Map<String, Object>) feature.get("geometry");
        // geometry.coordinates = [lon, lat]
        List<Double> coords = (List<Double>) geom.get("coordinates");
        double lon = coords.get(0);
        double lat = coords.get(1);

        String imageUrl = props.containsKey("preview")
                ? ((Map<String, Object>) props.get("preview")).get("source").toString()
                : "https://placehold.co/400x250";

        return new GlobDTO(
                props.get("xid").toString(),
                props.get("name").toString(),
                props.get("kinds").toString(),
                imageUrl,
                Double.parseDouble(props.get("dist").toString()),
                lat,
                lon
        );
    }
}
