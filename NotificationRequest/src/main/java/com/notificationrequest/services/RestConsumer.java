package com.notificationrequest.services;

import com.notificationrequest.model.NotificationRequest;
import com.notificationrequest.model.dto.NotificationRequestDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class RestConsumer {
    @CircuitBreaker(name = "channelService",fallbackMethod = "errorMethod")
    public String getAllChannels() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/channel", String.class);
        return response.getBody();
    }
    public String errorMethod(Exception e){
        throw new RuntimeException("Too many requests, try again later!");
    }

}
