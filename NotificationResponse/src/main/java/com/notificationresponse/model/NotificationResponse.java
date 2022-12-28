package com.notificationresponse.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name= "notificationResponse")
public  class NotificationResponse {
    enum State{
        sended,
        processed,
        received
    }

    @Id
    @GeneratedValue
    private int id;
    private String action;
    private String message;
    private String state;
    private int channel;
    private int request;

    public NotificationResponse(){

    }
    public NotificationResponse(String action, String message, String state, int channel, int request){
        this.action = action;
        this.message = message;
        this.state = State.valueOf(state).toString();
        this.channel = channel;
        this.request = request;
    }


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = State.valueOf(state).toString();
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }

    public int getRequest() {
        return request;
    }

    public void setRequest(int request) {
        this.request = request;
    }
}
