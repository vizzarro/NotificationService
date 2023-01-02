package com.notificationrequest.configuration;

import com.notificationrequest.services.RedisConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
@Configuration
public class RedisConfiguration {
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisConsumer());
    }
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("notificationRequestChannel");
    }
    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener(), topic());
        return container;
    }
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}