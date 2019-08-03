package com.mabellou.queuedemo.queue.consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.redis.inbound.RedisInboundChannelAdapter;
import org.springframework.integration.redis.inbound.RedisQueueMessageDrivenEndpoint;

@Configuration
@EnableIntegration
public class QueueIntegrationConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    public QueueIntegrationConfig(LettuceConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public DirectChannel receiverChannel(){
        return new DirectChannel();
    }

    @Bean
    public RedisQueueMessageDrivenEndpoint consumerEndpoint(){
        RedisQueueMessageDrivenEndpoint endpoint = new RedisQueueMessageDrivenEndpoint(
                "Redis-queue",
                redisConnectionFactory
        );
        endpoint.setOutputChannelName("receiverChannel");
        return endpoint;
    }
}
