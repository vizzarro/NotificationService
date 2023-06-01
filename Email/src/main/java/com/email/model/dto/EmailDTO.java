package com.email.model.dto;

public class EmailDTO {
    private int id;
    private String subject;
    private String text;
    private String filePath;
    private int response;

    private EmailDTO() {

    }

    public EmailDTO(String subject, String text, String filePath, int response) {
        this.subject = subject;
        this.text = text;
        this.filePath = filePath;
        this.response = response;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
