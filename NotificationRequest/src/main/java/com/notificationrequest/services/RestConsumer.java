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

    public NotificationRequestDTO getRequest(int id) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            ResponseEntity<NotificationRequestDTO> response = restTemplate.getForEntity("http://localhost:8080/notificationrequest/" + id, NotificationRequestDTO.class);
            return response.getBody();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }

    public void update(NotificationRequestDTO dto){
        //TODO fix update
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationRequestDTO> request = new HttpEntity<>(
                new NotificationRequestDTO(dto.getState().toString(), dto.getMessage(),dto.getType().toString(),dto.getAction().toString(),dto.getPriority().toString(),dto.isMulticast(),dto.getChangeField())
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8080/notificationrequest/"+dto.getId(), request, String.class);

    }

    public void updateRequestState(int id, String state){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationRequestDTO> request = new HttpEntity<>(
                getRequest(id)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8080/notificationrequest/"+id+"/"+state, request, String.class);

    }

    public String createNotificationResponse(String action, String message, String state, String type,String changeField, String priority, boolean multicast) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationRequest> request = new HttpEntity<>(

                new NotificationRequest(state, message, type, action, priority, multicast, changeField)
        );
        try{
            String productCreateResponse = restTemplate.postForObject("http://localhost:8080/notificationrequest", request, String.class);
            return productCreateResponse;
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


}
