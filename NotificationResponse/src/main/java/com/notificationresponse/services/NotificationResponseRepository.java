package com.notificationresponse.services;

import com.notificationresponse.model.NotificationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationResponseRepository  extends JpaRepository<NotificationResponse,Integer> { }
