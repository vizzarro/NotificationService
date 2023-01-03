package com.sms.services;

import com.sms.model.Sms;
import com.sms.model.dto.NotificationResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestConsumer {
    public NotificationResponseDTO getResponse(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationResponseDTO> response = restTemplate.getForEntity("http://localhost:8084/notificationresponse/"+id, NotificationResponseDTO.class);
        return response.getBody();
    }
    public String createSms(String message, int response){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Sms> request = new HttpEntity<>(
                new Sms(message, response)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8082/sms", request, String.class);
        System.out.println(productCreateResponse);
        return productCreateResponse;

    }

}
