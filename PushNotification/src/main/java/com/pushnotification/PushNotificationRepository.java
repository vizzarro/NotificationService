package com.pushnotification;

import com.pushnotification.model.PushNotification;
import org.springframework.data.repository.CrudRepository;

public interface PushNotificationRepository extends CrudRepository<PushNotification,Integer> { }