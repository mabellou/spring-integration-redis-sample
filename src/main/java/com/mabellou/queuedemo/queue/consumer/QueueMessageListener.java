package com.mabellou.queuedemo.queue.consumer;

import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class QueueMessageListener {

    @ServiceActivator(inputChannel = "receiverChannel")
    public void receiveFromQueue(Message<?> message){
        System.out.println("receiveFromQueue: " + message.toString());
    }

}
