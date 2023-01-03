package com.notificationresponse.model;

import com.notificationresponse.model.dto.Action;
import com.notificationresponse.model.dto.Type;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.notificationresponse.model.dto.State;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name= "response_notification")
public  class NotificationResponse {

    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message = "the action field is required")
    private String action;
    @NotEmpty(message = "the message field is required")
    private String message;
    @NotEmpty(message = "the message field is required")
    private String state;
    @NotEmpty(message = "the message field is required")
    private String type;
    @NotNull(message = "the channel reference must not be null ")
    private int channel;
    @NotNull(message = "the request reference must not be null")
    private int request;

    public NotificationResponse(){

    }
    public NotificationResponse(String action, String message, String state,String type, int channel, int request){
        this.action = Action.valueOf(action).toString();
        this.message = message;
        this.state = State.valueOf(state).toString();
        this.type = Type.valueOf(type).toString();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
