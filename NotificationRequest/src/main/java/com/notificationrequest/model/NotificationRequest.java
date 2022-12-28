package com.notificationrequest.model;

import com.notificationrequest.model.dto.Priority;
import com.notificationrequest.model.dto.State;
import com.notificationrequest.model.dto.Type;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "notification_request")
public class NotificationRequest {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String state;
    private String message;// message source?
    private String type;
    private LocalDate date;
    private LocalDateTime updateDate;
    private LocalTime time;
    private String priority;
    private boolean multicast;

    public NotificationRequest(){
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }
    public NotificationRequest(String state, String message, String type, String priority, Boolean multicast){
        this.state = State.valueOf(state).toString();
        this.message= message;
        this.type = Type.valueOf(type).toString();
        this.date = LocalDate.now();
        this.time = LocalTime.now();
        this.updateDate = null;
        this.priority = Priority.valueOf(priority).toString();
        this.multicast = multicast;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = State.valueOf(state).toString();
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
        this.type = Type.valueOf(type).toString();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = Priority.valueOf(priority).toString();
    }

    public boolean isMulticast() {
        return multicast;
    }

    public void setMulticast(boolean multicast) {
        this.multicast = multicast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
