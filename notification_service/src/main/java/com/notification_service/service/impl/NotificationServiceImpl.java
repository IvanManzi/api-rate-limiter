package com.notification_service.service.impl;

import com.model.NotificationRequest;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import com.notification_service.exception.TooManyRequestsException;
import com.notification_service.service.NotificationService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final Map<String, Bucket> clientBuckets = new ConcurrentHashMap<>();
    private final Bucket globalBucket = Bucket4j.builder()
            .addLimit(Bandwidth.classic(100, Refill.intervally(100, Duration.ofSeconds(1))))
            .build(); // 100 requests per second
    private final Bandwidth clientLimit = Bandwidth.classic(10, Refill.intervally(10, Duration.ofSeconds(1)));
    private final Map<String, AtomicInteger> monthlyRequests = new ConcurrentHashMap<>();

    @Override
    public void sendNotification(NotificationRequest request) throws TooManyRequestsException {
        String clientId = request.getClientId();
        if (clientId == null || clientId.isEmpty()) {
            throw new IllegalArgumentException("clientId cannot be empty");
        }
        Bucket clientBucket = clientBuckets.computeIfAbsent(clientId, k -> Bucket4j.builder()
                .addLimit(clientLimit)
                .build());
        if (globalBucket.tryConsume(1) && clientBucket.tryConsume(1)) {
            // Increment monthly request count for this client
            AtomicInteger monthlyCount = monthlyRequests.computeIfAbsent(clientId, k -> new AtomicInteger(0));
            monthlyCount.incrementAndGet();

            // send notification
        } else {
            throw new TooManyRequestsException("Client has exceeded request limit");
        }
        if (monthlyRequests.get(clientId).get() > 10000) { /*limit 10,000 requests per month*/
            throw new TooManyRequestsException("Client has exceeded monthly request limit");
        }
    }
}



