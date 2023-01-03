package com.pushnotification.services;


import com.pushnotification.model.PushNotification;
import com.pushnotification.model.dto.NotificationResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestConsumer {
    public NotificationResponseDTO getResponse(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationResponseDTO> response = restTemplate.getForEntity("http://localhost:8084/notificationresponse/"+id, NotificationResponseDTO.class);
        return response.getBody();
    }
    public String createPushNotification(String topic, String title, String message, int response){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<PushNotification> request = new HttpEntity<>(
                new PushNotification(topic, title,message, response)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8083/pushnotification", request, String.class);
        System.out.println(productCreateResponse);
        return productCreateResponse;

    }

}
