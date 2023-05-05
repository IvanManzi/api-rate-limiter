package com.notification_service.service;

import com.model.NotificationRequest;
import com.notification_service.exception.TooManyRequestsException;

public interface NotificationService {

    void sendNotification(NotificationRequest request) throws TooManyRequestsException;
}
