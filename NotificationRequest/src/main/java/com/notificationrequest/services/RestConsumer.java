package com.notificationrequest.services;

import com.notificationrequest.model.dto.ChannelDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class RestConsumer {
    Logger logger = Logger.getLogger(RestConsumer.class.getName());

    public ChannelDTO getChannel(String name) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ChannelDTO> response = restTemplate.getForEntity("http://localhost:8081/channel/channels?name=" + name, ChannelDTO.class);
        logger.log(Level.WARNING, response.getStatusCode().toString());
        return response.getBody();
    }


}
