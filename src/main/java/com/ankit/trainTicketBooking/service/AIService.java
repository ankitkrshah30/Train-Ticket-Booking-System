package com.ankit.trainTicketBooking.service;

import com.ankit.trainTicketBooking.config.WebClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class AIService {

    @Value("${ai-api-key}")
    private String apiKey;

    @Value("${ai-api-url}")
    private String apiUrl;

    private final WebClient webClient;

    public AIService(WebClient webClient) {
        this.webClient = webClient;
    }

    public String getAnswer(String question){
        //Construct request payLoad
        //{
        //  "contents": [{
        //    "parts":[{"text": "what is its current status of the train 22817."}]
        //    }]
        //   }
        Map<String,Object> requestBody=Map.of(
          "contents",new Object[]{
                  Map.of("parts",new Object[]{
                          Map.of("text",question)
                  })
                }
        );
        return webClient.post()
                .uri(apiUrl+apiKey)
                .header("Content-Type", "application/json")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(String.class) // or use a POJO for better parsing
                .block();
    }
}
