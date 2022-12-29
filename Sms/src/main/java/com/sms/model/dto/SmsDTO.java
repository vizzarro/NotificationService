package com.sms.model.dto;

public class SmsDTO {
    private int id;
    private String message;
    private int response;
    public  SmsDTO(){

    }
    public SmsDTO(String message, int response){
        this.message=message;
        this.response=response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
