package com.notificationresponse.services;


import com.notificationresponse.model.NotificationResponse;
import com.notificationresponse.model.dto.NotificationRequestDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestConsumer {
    public NotificationRequestDTO getRequest( int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationRequestDTO> response = restTemplate.getForEntity("http://localhost:8080/notificationrequest/"+id, NotificationRequestDTO.class);
        return response.getBody();
    }
    public void createNotificationResponse(String action, String message, String state, String type, int channel, int req) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationResponse> request = new HttpEntity<>(
                new NotificationResponse(action, message, state, type, channel, req)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8084/notificationresponse", request, String.class);
        System.out.println(productCreateResponse);
    }
}
