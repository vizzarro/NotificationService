package com.sms.services;

import com.sms.model.Sms;
import com.sms.model.dto.NotificationResponseDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;


public class RestConsumer {
    Logger logger= Logger.getLogger(RestConsumer.class.getName());

    public NotificationResponseDTO getResponse(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationResponseDTO> response = restTemplate.getForEntity("http://localhost:8084/notificationresponse/"+id, NotificationResponseDTO.class);
        return response.getBody();
    }
    public NotificationResponseDTO smsHandler(String s, Throwable e){
        return  null;
    }
    public String createSms(String message, int response){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Sms> request = new HttpEntity<>(
                new Sms(message, response)
        );

        String productCreateResponse = restTemplate.postForObject("http://localhost:8082/sms", request, String.class);
        logger.log(Level.INFO,productCreateResponse);;
        return productCreateResponse;

    }

}
