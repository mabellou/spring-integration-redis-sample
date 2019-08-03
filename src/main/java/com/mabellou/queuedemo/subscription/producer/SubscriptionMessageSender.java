package com.mabellou.queuedemo.subscription.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.redis.outbound.RedisPublishingMessageHandler;
import org.springframework.integration.redis.outbound.RedisQueueOutboundChannelAdapter;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMessageSender {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @ServiceActivator(inputChannel = "publishChannel")
    public void sendMessageToPublish(Message<?> message){
        System.out.println("sendMessageToPublish: " + message.toString());
        RedisPublishingMessageHandler adapter = new RedisPublishingMessageHandler(redisConnectionFactory);
        adapter.setTopic("haha.hehe");
        adapter.setSerializer(new JdkSerializationRedisSerializer());
        adapter.handleMessage(message);
    }
}
