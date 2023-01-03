package com.sms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "sms")
public class Sms {
    @Id
    @GeneratedValue
    private int id;
    @NotEmpty(message = "the message field is required")
    private String message;
    @NotNull(message = "the response reference must not be null")
    private int response;
    public  Sms(){

    }
    public Sms(String message, int response){
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
