package com.notificationresponse.model.dto;

public class NotificationResponseDTO {
    private int id;
    private String action;
    private String message;
    private State state;
    private Type type;
    private int channel;
    private int request;
    public NotificationResponseDTO(){

    }
    public NotificationResponseDTO(String action, String message, String state, String type,int channel, int request){
        this.action = action;
        this.message = message;
        this.state = State.valueOf(state);
        this.type = Type.valueOf(type);
        this.channel = channel;
        this.request = request;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
