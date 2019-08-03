package com.mabellou.queuedemo.queue.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class QueueMessageSender {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @ServiceActivator(inputChannel = "redisChannel")
    public void sendMessageToQueue(Message<?> message){
        System.out.println("sendMessageToQueue: " + message.toString());
        RedisQueueOutboundChannelAdapter adapter = new RedisQueueOutboundChannelAdapter("Redis-queue", redisConnectionFactory);
        adapter.handleMessage(message);
    }
}
