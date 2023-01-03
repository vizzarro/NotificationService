package com.email.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name= "email1_table")
public class Email {
    @Id
    @GeneratedValue
    private int id;
    private String subject;
    @NotEmpty(message = "the mail body is required")
    private String text;
    private String filePath;
    @NotEmpty(message = "the response reference must not be empty")
    private int response;

    private Email(){

    }
    public Email(String subject, String text, String filePath, int response){
        this.subject= subject;
        this.text=text;
        this.filePath=filePath;
        this.response=response;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
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
