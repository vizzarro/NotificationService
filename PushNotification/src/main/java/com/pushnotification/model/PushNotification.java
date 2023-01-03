package com.pushnotification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name= "pushNotification")
public class PushNotification {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message = "the topic field is required")
    private String topic;
    @NotEmpty(message = "the title field is required")
    private String title;
    @NotEmpty(message = "the body field is required")
    private String body;
    @NotNull(message = "the response reference must not be null")
    private int response;

    public PushNotification(){

    }
    public PushNotification(String topic, String title, String body, int response){
        this.topic = topic;
        this.title = title;
        this.body = body;
        this.response = response;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
