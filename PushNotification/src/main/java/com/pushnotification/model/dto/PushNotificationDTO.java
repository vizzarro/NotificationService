package com.pushnotification.model.dto;

public class PushNotificationDTO {
    private int id;
    private String topic;
    private String title;
    private String body;
    private int response;
    public PushNotificationDTO(){}
    public PushNotificationDTO(String topic, String title, String body, int response){
        this.topic = topic;
        this.title = title;
        this.body = body;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
    }
}
