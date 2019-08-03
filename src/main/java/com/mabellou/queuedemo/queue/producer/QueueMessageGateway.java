package com.mabellou.queuedemo.queue.producer;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface QueueMessageGateway {

    @Gateway(requestChannel = "redisChannel")
    <S> void sendMessage(S request);
}
