package com.notificationrequest.services;

import com.notificationrequest.model.NotificationRequest;
import com.notificationrequest.model.dto.NotificationRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;


public class RestConsumer {
    public void getRequests() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/notificationrequest", String.class);
        System.out.println(response.getBody());
    }
    public void createNotification() {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationRequest> request = new HttpEntity<NotificationRequest>(
                new NotificationRequest("sended","my message","sms","low",false)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8080/notificationrequest", request, String.class);
        System.out.println(productCreateResponse);
    }
}
