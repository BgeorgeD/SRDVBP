package com.example.vacationrecommender.service;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PixabayService {

    @Value("${pixabay.api.key}")
    private String apiKey;

    public String getImageUrlForHotel(String hotelName) {
        try {
            String query = URLEncoder.encode(hotelName, "UTF-8");
            String url = "https://pixabay.com/api/?key=" + apiKey + "&q=" + query + "&image_type=photo&per_page=3";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());
            JSONArray hits = json.getJSONArray("hits");

            if (!hits.isEmpty()) {
                return hits.getJSONObject(0).getString("webformatURL");
            } else {
                return "/images/default-hotel.jpg";
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "/images/default-hotel.jpg";
        }
    }
}
