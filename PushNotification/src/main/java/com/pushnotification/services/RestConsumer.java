package com.pushnotification.services;


import com.pushnotification.model.PushNotification;
import com.pushnotification.model.dto.NotificationRequestDTO;
import com.pushnotification.model.dto.NotificationResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;


public class RestConsumer {
    Logger logger = Logger.getLogger(RestConsumer.class.getName());
    @CircuitBreaker(name= "pushService", fallbackMethod = "cacheSearch")
    public NotificationResponseDTO getResponse(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationResponseDTO> response = restTemplate.getForEntity("http://localhost:8084/notificationresponse/"+id, NotificationResponseDTO.class);
        return response.getBody();
    }
    public NotificationResponseDTO cacheSearch(String s, Throwable e){
        logger.log(Level.INFO, "circuit open");
        return null;
    }

    @CircuitBreaker(name= "newPush", fallbackMethod = "errorString")
    public String createPushNotification(String topic, String title, String message, int response){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<PushNotification> request = new HttpEntity<>(
                new PushNotification(topic, title,message, response)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8083/pushnotification", request, String.class);
        System.out.println(productCreateResponse);
        return productCreateResponse;
    }
    public String errorString(String s, Throwable e){
        logger.log(Level.INFO, "circuit open");
        return "too many notifications retry later!";
    }
    public NotificationRequestDTO getRequest(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationRequestDTO> response = restTemplate.getForEntity("http://localhost:8080/notificationrequest/"+id, NotificationRequestDTO.class);
        return response.getBody();
    }
    public void updateRequestState(int id, String state){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationRequestDTO> request = new HttpEntity<>(
                getRequest(id)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8080/notificationrequest/"+id+"/"+state, request, String.class);
    }

    public void updateResponseState(int id, String state){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationResponseDTO> request = new HttpEntity<>(
                getResponse(id)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8084/notificationresponse/"+id+"/"+state, request, String.class);
    }


}
