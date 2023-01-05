package com.notificationresponse.services;


import com.notificationresponse.model.NotificationResponse;
import com.notificationresponse.model.dto.Action;
import com.notificationresponse.model.dto.NotificationRequestDTO;
import com.notificationresponse.model.dto.NotificationResponseDTO;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestConsumer {
    public NotificationRequestDTO getRequest(int id) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<NotificationRequestDTO> response = restTemplate.getForEntity("http://localhost:8080/notificationrequest/" + id, NotificationRequestDTO.class);
        return response.getBody();
    }

    public String createNotificationResponse(String action, String message, String state, String type,String actionField, int channel, int req) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationResponse> request = new HttpEntity<>(
                new NotificationResponse(action, message, state, type,actionField, channel, req)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8084/notificationresponse", request, String.class);
        System.out.println(productCreateResponse);
        return productCreateResponse;
    }

    public void update(NotificationResponseDTO dto){
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<NotificationResponseDTO> request = new HttpEntity<>(
                new NotificationResponseDTO(dto.getAction().toString(), dto.getMessage(),dto.getState().toString(),dto.getType().toString(),dto.getChangeField(),dto.getChannel(),dto.getRequest())
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8084/notificationresponse/"+dto.getId(), request, String.class);

    }

}
