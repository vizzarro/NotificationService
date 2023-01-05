package com.email.model.dto;

import jakarta.validation.constraints.NotEmpty;

public class NotificationResponseDTO {
    private int id;
    private Action action;
    private String message;
    private State state;
    private Type type;
    private String changeField;
    private int channel;
    @NotEmpty(message = "the request reference must not be empty")
    private int request;
    public NotificationResponseDTO(){

    }
    public NotificationResponseDTO(String action, String message, String state, String type, String changeField, int channel, int request){
        this.action = Action.valueOf(action);
        this.message = message;
        this.state = State.valueOf(state);
        this.type = Type.valueOf(type);
        this.changeField = changeField;
        this.channel = channel;
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getChangeField() {
        return changeField;
    }

    public void setChangeField(String changeField) {
        this.changeField = changeField;
    }
}
