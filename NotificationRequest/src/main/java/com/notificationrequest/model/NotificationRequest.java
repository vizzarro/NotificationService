package com.notificationrequest.model;

import com.notificationrequest.model.dto.Action;
import com.notificationrequest.model.dto.Priority;
import com.notificationrequest.model.dto.State;
import com.notificationrequest.model.dto.Type;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "notification_request")
public class NotificationRequest {


    @Id
    @GeneratedValue
    private Long id;

    private String state;
    @NotEmpty(message = "message field is required")
    private String message;// message source?
    @NotEmpty(message = "type field is required")
    private String type;
    @NotEmpty(message = "action field is required")
    private String action;//vedere se esiste il default o metterlo tramite sql
    private LocalDate date;
    private LocalDateTime updateDate;
    private LocalTime time;
    @NotEmpty(message = "priority field is required")
    private String priority;
    @NotNull(message = "multicast field is required")
    private boolean multicast;

    public NotificationRequest(){
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }
    public NotificationRequest(String state, String message, String type, String action,String priority, Boolean multicast){
        this.state = State.valueOf(state).toString();
        this.message= message;
        this.type = Type.valueOf(type).toString();
        this.action= Action.valueOf(action).toString();
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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
