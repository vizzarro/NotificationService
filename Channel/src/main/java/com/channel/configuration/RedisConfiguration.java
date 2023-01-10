package com.channel.configuration;

import com.channel.services.RedisEmailConsumer;
import com.channel.services.RedisPushConsumer;
import com.channel.services.RedisRequestConsumer;
import com.channel.services.RestConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

@Configuration
public class RedisConfiguration {


    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(new MessageListenerAdapter(new RedisRequestConsumer(new RestConsumer())), new ChannelTopic("notificationRequest"));
        container.addMessageListener(new MessageListenerAdapter(new RedisEmailConsumer(new RestConsumer())), new ChannelTopic("sms"));
        container.addMessageListener(new MessageListenerAdapter(new RedisEmailConsumer(new RestConsumer())), new ChannelTopic("email"));
        container.addMessageListener(new MessageListenerAdapter(new RedisPushConsumer(new RestConsumer())), new ChannelTopic("push"));
        return container;
    }
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
