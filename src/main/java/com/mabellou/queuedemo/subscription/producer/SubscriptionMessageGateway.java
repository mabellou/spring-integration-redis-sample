package com.mabellou.queuedemo.subscription.producer;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface SubscriptionMessageGateway {

    @Gateway(requestChannel = "publishChannel")
    <S> void publish(S request);
}
