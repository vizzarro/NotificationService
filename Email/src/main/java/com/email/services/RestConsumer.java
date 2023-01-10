package com.email.services;


import com.email.model.Email;
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
    public NotificationResponseDTO getResponse(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationResponseDTO> response = restTemplate.getForEntity("http://localhost:8084/notificationresponse/"+id, NotificationResponseDTO.class);
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

}
