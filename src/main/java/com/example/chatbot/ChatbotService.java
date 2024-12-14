package com.example.chatbot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ChatbotService {

    @Value("${huggingface.api.key}")
    private String apiKey;

    private static final String HUGGINGFACE_API_URL = "https://api-inference.huggingface.co/models/gpt2";

    public String getChatbotResponse(String message) {
        try {
            // Create the request body
            String requestBody = "{\"inputs\": \"" + message + "\"}";

            // Set the headers for the request
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + apiKey);
            headers.set("Content-Type", "application/json");

            HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);

            // Send the POST request to Hugging Face
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(
                    HUGGINGFACE_API_URL, HttpMethod.POST, entity, String.class);

            // Parse and return the response
            String responseBody = response.getBody();
            return responseBody;

        } catch (Exception e) {
            e.printStackTrace();
            return "Sorry, I couldn't process your request.";
        }
    }
}