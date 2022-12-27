package com.notificationrequest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Time;
import java.util.Date;
@Entity
@Table(name = "notificationRequest")
public class NotificationRequest {
    @Id
    @GeneratedValue
    private int id;
    private String state;
    private String message;
    private String type;
    private Date date;
    private Time time;
    private String priority;
    private boolean multicast;

    public NotificationRequest(){

    }
    public NotificationRequest(String state, String message, String type, Date date, Time time, String priority, Boolean multicast){
        this.state = state;
        this.message= message;
        this.type = type;
        this.date = date;
        this.time = time;
        this.priority = priority;
        this.multicast = multicast;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public boolean isMulticast() {
        return multicast;
    }

    public void setMulticast(boolean multicast) {
        this.multicast = multicast;
    }
}
