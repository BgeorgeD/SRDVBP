package com.example.vacationrecommender.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TranslationService {

    @Value("${deepl.api.key}")
    private String apiKey;

    @Value("${deepl.api.url}")
    private String deeplUrl;

    public String translateToEnglish(String text) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Map<String, String> params = new HashMap<>();
        params.put("auth_key", apiKey);
        params.put("text", text);
        params.put("target_lang", "EN");

        String body = "auth_key=" + apiKey + "&text=" + text + "&target_lang=EN";

        HttpEntity<String> request = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(deeplUrl, request, Map.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                List<Map<String, String>> translations = (List<Map<String, String>>) response.getBody().get("translations");
                return translations.get(0).get("text");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return text; // dacă nu merge traducerea, returnează originalul
    }
}
