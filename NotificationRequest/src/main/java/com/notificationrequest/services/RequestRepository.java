package com.notificationrequest.services;

import com.notificationrequest.model.NotificationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<NotificationRequest,Integer> { }
