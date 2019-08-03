package com.mabellou.queuedemo.subscription.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.integration.annotation.Filter;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class SubscriptionMessageListener {

    private static final Duration TTL_SETNX = Duration.ofHours(1);

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    Environment env;

    @Filter(inputChannel = "subscriberChannel", outputChannel = "filteredMessage")
    public boolean filterAlreadyProcessedMessages(Message<?> message){
        System.out.println("filterAlreadyProcessedMessages: " + message.toString());
        String key = appName + ":" + message.getHeaders().getId();
        Boolean isAbsent = redisTemplate.opsForValue().setIfAbsent(key, "1", TTL_SETNX);
        if(isAbsent == null){
            throw new RuntimeException("Cannot setnx on redis for message " + message);
        }
        return isAbsent;
    }

    @ServiceActivator(inputChannel = "filteredMessage")
    public void receiveFromSubscribe(Message<?> message){
        System.out.println("receiveFromSubscribe: " + message.toString());
    }

}
