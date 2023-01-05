package com.channel.services;

import com.channel.model.Channel;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class RestConsumer {
    public void getRequests() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8081/channel", String.class);
        System.out.println(response.getBody());
    }
    public void createChannel(String name, String info) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Channel> request = new HttpEntity<>(
                new Channel(name,info)
        );
        String productCreateResponse = restTemplate.postForObject("http://localhost:8081/channel", request, String.class);
        System.out.println(productCreateResponse);
    }
}
