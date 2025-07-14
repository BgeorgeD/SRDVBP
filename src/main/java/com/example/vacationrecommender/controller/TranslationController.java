package com.example.vacationrecommender.controller;


import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TranslationController {

    @Value("${deepl.api.key}")
    private String deeplApiKey;

    @PostMapping("/translate")
    public ResponseEntity<Map<String, String>> translateText(@RequestBody Map<String, String> payload) {
        String text = payload.get("text");
        String targetLang = payload.getOrDefault("targetLang", "RO");

        String translatedText = translateWithDeepL(text, targetLang);
        return ResponseEntity.ok(Map.of("translatedText", translatedText));
    }

    private String translateWithDeepL(String text, String targetLang) {
        String url = "https://api-free.deepl.com/v2/translate";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "DeepL-Auth-Key " + deeplApiKey);
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        String[] parts = text.split("\\|\\|\\|");
        for (String part : parts) {
            requestBody.add("text", part.trim());
        }

        requestBody.add("target_lang", targetLang);

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);

        try {
            JSONObject json = new JSONObject(response.getBody());
            JSONArray translations = json.getJSONArray("translations");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < translations.length(); i++) {
                sb.append(translations.getJSONObject(i).getString("text"));
                if (i < translations.length() - 1) {
                    sb.append("|||"); // separator între titlu, locație și descriere
                }
            }

            return sb.toString();
        } catch (Exception e) {
            return "[Eroare la traducere]";
        }

    }
}

