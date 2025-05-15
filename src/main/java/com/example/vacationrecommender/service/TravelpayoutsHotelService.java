package com.example.vacationrecommender.service;

import com.example.vacationrecommender.dto.VacationDTO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class TravelpayoutsHotelService {

    public List<VacationDTO> getHotelsForLocation(String location) {
        List<VacationDTO> hotels = new ArrayList<>();

        try {
            String url = "https://engine.hotellook.com/api/v2/cache.json"
                    + "?location=" + location
                    + "&checkIn=2025-06-01"
                    + "&checkOut=2025-06-05"
                    + "&currency=EUR"
                    + "&limit=5";



            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String body = response.body();
            System.out.println("🟡 Răspuns primit de la Travelpayouts:\n" + body);

            // Verificăm că răspunsul este un JSONArray valid
            if (!body.trim().startsWith("[")) {
                System.out.println("⚠️ Răspunsul NU este un JSONArray valid. Locația poate fi greșită sau goală.");
                return hotels;
            }

            JSONArray jsonArray = new JSONArray(body);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject obj = jsonArray.getJSONObject(i);

                String hotelName = obj.optString("hotelName", "Hotel Necunoscut");
                int price = obj.optInt("priceFrom", 0);

                VacationDTO dto = new VacationDTO();
                dto.setDestinatie(hotelName);
                dto.setDescriere("Hotel generat automat din API");
                dto.setTip("nespecificat");
                dto.setPret(price);
                dto.setImageUrl(null); // va fi completat de PixabayService

                hotels.add(dto);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println("❌ Eroare la apelul Travelpayouts: " + e.getMessage());
            e.printStackTrace();
        }

        return hotels;
    }
}
