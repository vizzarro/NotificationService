package com.channel.services;

import com.channel.model.Channel;
import com.channel.model.dto.NotificationRequestDTO;
import com.channel.model.dto.NotificationResponseDTO;
import com.channel.model.dto.State;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestConsumer {
    public NotificationRequestDTO getRequest(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationRequestDTO> response = restTemplate.getForEntity("http://localhost:8080/notificationrequest/"+id, NotificationRequestDTO.class);
        return response.getBody();
    }
    public NotificationResponseDTO getResponse(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationResponseDTO> response = restTemplate.getForEntity("http://localhost:8084/notificationresponse/"+id, NotificationResponseDTO.class);
        return response.getBody();
    }
    public void createChannel(String name, String info) {
        RestTemplate restTemplate = new RestTemplate();

        HttpEntity<Channel> request = new HttpEntity<>(
                new Channel(name,info)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8081/channel", request, String.class);
        System.out.println(productCreateResponse);
    }

    public void updateRequestState(int id, String state){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationRequestDTO> request = new HttpEntity<>(
                getRequest(id)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8080/notificationrequest/"+id+"/"+state, request, String.class);
    }
}
