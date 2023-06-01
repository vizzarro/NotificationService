package com.notificationresponse.configuration;


import com.notificationresponse.services.NotificationResponseParser;
import com.notificationresponse.services.RedisConsumer;
import com.notificationresponse.services.RedisProducer;
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
    RestConsumer restConsumer = new RestConsumer();

    @Bean
    MessageListenerAdapter messageListener(RedisConnectionFactory connectionFactory) {
        return new MessageListenerAdapter(new RedisConsumer(restConsumer, new NotificationResponseParser(new RedisProducer(this.template(connectionFactory)), restConsumer)));
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("notificationRequest");
    }

    @Bean
    RedisMessageListenerContainer redisContainer(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container
                = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(messageListener(connectionFactory), topic());
        return container;
    }

    @Bean
    StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
