package com.email.services;


import com.email.model.Email;
import com.email.model.dto.NotificationRequestDTO;
import com.email.model.dto.NotificationResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RestConsumer {
    Logger logger= Logger.getLogger(RestConsumer.class.getName());
    public NotificationRequestDTO getResponse(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationRequestDTO> response = restTemplate.getForEntity("http://localhost:8080/notificationrequest/"+id, NotificationRequestDTO.class);
        return response.getBody();
    }
    public String createEmail(String subject, String message, String fieldPath,int response){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Email> request = new HttpEntity<>(
                new Email(subject,message, fieldPath,response)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8085/email", request, String.class);
        logger.log(Level.INFO,productCreateResponse);
        return productCreateResponse;

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
        HttpEntity<NotificationRequestDTO> request = new HttpEntity<>(
                getResponse(id)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8080/notificationrequest/"+id+"/"+state, request, String.class);
    }


}
