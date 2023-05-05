package com.notification_service.service.impl;

import com.model.NotificationRequest;
import com.notification_service.exception.TooManyRequestsException;
import com.notification_service.service.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NotificationServiceTest {
    @Autowired
    private NotificationService notificationService;

    @Test
    public void testValidNotificationRequest() throws Exception {
        NotificationRequest request = new NotificationRequest("client1", "Hello World");
        notificationService.sendNotification(request);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNotificationRequestWithoutClientId() throws Exception {
        NotificationRequest request = new NotificationRequest("", "Hello World");
        notificationService.sendNotification(request);
    }

    @Test
    public void testFirstNotificationRequestFromClient() throws Exception {
        NotificationRequest request = new NotificationRequest("client1", "Hello World");
        notificationService.sendNotification(request);
    }

    @Test
    public void testNotificationRequestWithinRateLimit() throws Exception {
        NotificationRequest request = new NotificationRequest("client1", "Hello World");
        notificationService.sendNotification(request);

        request = new NotificationRequest("client1", "Hello World");
        notificationService.sendNotification(request);
    }

    @Test(expected = TooManyRequestsException.class)
    public void testNotificationRequestExceedingClientRateLimit() throws Exception {
        for (int i = 0; i < 12; i++) {
            NotificationRequest request = new NotificationRequest("client2", "Hello World");
            notificationService.sendNotification(request);
        }
    }

    @Test(expected = TooManyRequestsException.class)
    public void testNotificationRequestExceedingGlobalRateLimit() throws Exception {
        for (int i = 0; i < 110; i++) {
            NotificationRequest request = new NotificationRequest("client3", "Hello World");
            notificationService.sendNotification(request);
        }
    }
}
