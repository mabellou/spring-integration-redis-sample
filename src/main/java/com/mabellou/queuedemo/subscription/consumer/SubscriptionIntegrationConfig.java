package com.mabellou.queuedemo.subscription.consumer;

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
public class SubscriptionIntegrationConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    public SubscriptionIntegrationConfig(LettuceConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    @Bean
    public PublishSubscribeChannel subscriberChannel(){
        return new PublishSubscribeChannel();
    }


    @Bean
    public RedisInboundChannelAdapter subscriberEndpoint(){
        RedisInboundChannelAdapter endpoint = new RedisInboundChannelAdapter(
                redisConnectionFactory
        );
        endpoint.setTopicPatterns("haha.*");
        endpoint.setOutputChannelName("subscriberChannel");
        endpoint.setSerializer(new JdkSerializationRedisSerializer());
        return endpoint;
    }
}
