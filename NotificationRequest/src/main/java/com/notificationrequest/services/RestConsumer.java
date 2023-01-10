package com.notificationrequest.services;

import com.notificationrequest.model.NotificationRequest;
import com.notificationrequest.model.dto.ChannelDTO;
import com.notificationrequest.model.dto.NotificationRequestDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RestConsumer {
    Logger logger = Logger.getLogger(RestConsumer.class.getName());

    public ChannelDTO getChannel(String name) {
        RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<ChannelDTO> response = restTemplate.getForEntity("http://localhost:8081/channel/channels?name="+name, ChannelDTO.class);
            logger.log(Level.WARNING,response.getStatusCode().toString());
            return response.getBody();
    }


}
