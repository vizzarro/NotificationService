package com.sms.services;

import com.sms.model.Sms;
import com.sms.model.dto.NotificationResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
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
    public NotificationResponseDTO smsHandler(String s, Throwable e){
        return  null;
    }
    public String createSms(String message, int response){
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        HttpEntity<Sms> request = new HttpEntity<>(
                new Sms(message, response), headers
        );

        String productCreateResponse = restTemplate.postForObject("http://localhost:8082/sms", request, String.class);
        logger.log(Level.INFO,productCreateResponse);;
        return productCreateResponse;

    }

}
