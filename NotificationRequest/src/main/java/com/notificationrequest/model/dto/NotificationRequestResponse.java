package com.notificationrequest.model.dto;

import java.util.List;

public class NotificationRequestResponse {
    private List<NotificationRequestDTO> data;
    public NotificationRequestResponse(){}

    public List<NotificationRequestDTO> getData() {
        return data;
    }

    public void setData(List<NotificationRequestDTO> data) {
        this.data = data;
    }
}
