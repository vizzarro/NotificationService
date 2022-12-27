package com.sms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sms")
public class Sms {
    @Id
    @GeneratedValue
    private int id;
    private String message;
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
}
