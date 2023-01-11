package com.notificationresponse.services;


import com.notificationresponse.model.NotificationResponse;
import com.notificationresponse.model.dto.ChannelDTO;
import com.notificationresponse.model.dto.NotificationRequestDTO;
import com.notificationresponse.model.dto.NotificationResponseDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class RestConsumer {
    Logger logger = Logger.getLogger(RestConsumer.class.getName());
    private List<ChannelDTO> cache = new ArrayList<>();

    public RestConsumer(){
        cache.add(new ChannelDTO(1,"notificationRequest", "channel for communication between Request and Response"));
        cache.add(new ChannelDTO(2,"sms","channel for communication between Response and Sms"));
        cache.add(new ChannelDTO(3,"email","channel for communication between Response and Email"));
        cache.add(new ChannelDTO(4,"push","channel for communication between Response and Push Notification"));
    }


    public NotificationRequestDTO getRequest(int id) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<NotificationRequestDTO> response = restTemplate.getForEntity("http://localhost:8080/notificationrequest/" + id, NotificationRequestDTO.class);
            return response.getBody();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public ChannelDTO getChannel(String name) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<ChannelDTO> response = restTemplate.getForEntity("http://localhost:8081/channel/channels?name="+name, ChannelDTO.class);
            return response.getBody();
        } catch (Exception ex){
            List<ChannelDTO> dto =  cache.stream().filter(e-> e.getName().equals(name)).toList();
            return dto.get(0);
        }
    }

    public String createNotificationResponse(String action, String message, String state, String type,String actionField, int channel, int req) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationResponse> request = new HttpEntity<>(
                new NotificationResponse(action, message, state, type,actionField, channel, req)
        );
        try{
            String productCreateResponse = restTemplate.postForObject("http://localhost:8084/notificationresponse", request, String.class);
            return productCreateResponse;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    public void update(NotificationResponseDTO dto){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationResponseDTO> request = new HttpEntity<>(
                new NotificationResponseDTO(dto.getAction().toString(), dto.getMessage(),dto.getState().toString(),dto.getType().toString(),dto.getChangeField(),dto.getChannel(),dto.getRequest())
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8084/notificationresponse/"+dto.getId(), request, String.class);

    }
    public void updateRequestState(int id, String state){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationRequestDTO> request = new HttpEntity<>(
                getRequest(id)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8080/notificationrequest/"+id+"/"+state, request, String.class);
    }

}
