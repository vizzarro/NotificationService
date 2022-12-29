package com.pushnotification.services;

import com.pushnotification.model.PushNotification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PushNotificationRepository extends JpaRepository<PushNotification,Integer> { }