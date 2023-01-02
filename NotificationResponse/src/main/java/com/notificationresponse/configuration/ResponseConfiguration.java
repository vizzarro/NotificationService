package com.notificationresponse.configuration;


import com.notificationresponse.services.RedisConsumer;
import com.notificationresponse.services.RestConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ResponseConfiguration {
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisConsumer(new RestConsumer()));
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
        container.addMessageListener(messageListener(), new ChannelTopic("email"));
        container.addMessageListener(messageListener(), new ChannelTopic("sms"));
        container.addMessageListener(messageListener(), new ChannelTopic("push"));
        return container;
    }
    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
}
