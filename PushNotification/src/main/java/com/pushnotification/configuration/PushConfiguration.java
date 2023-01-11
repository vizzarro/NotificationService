package com.pushnotification.configuration;

import com.google.auth.oauth2.AccessToken;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.pushnotification.services.PushNotificationParser;
import com.pushnotification.services.RedisConsumer;
import com.pushnotification.services.RestConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.io.IOException;
import java.io.InputStream;


@Configuration
public class PushConfiguration {
    @Value("${app.firebase-configuration-file}")
    private String fireBaseConfig;
    @Bean
    MessageListenerAdapter messageListener() {
        return new MessageListenerAdapter(new RedisConsumer(new RestConsumer(),new PushNotificationParser(firebaseMessaging(firebaseApp(googleCredentials())),new RestConsumer())));
    }
    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("push");
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
    @Bean
    FirebaseMessaging firebaseMessaging(FirebaseApp firebaseApp) {
        return FirebaseMessaging.getInstance(firebaseApp);
    }
    @Bean
    FirebaseApp firebaseApp(GoogleCredentials credentials) {
        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(credentials)
                .build();

        return FirebaseApp.initializeApp(options);
    }
    @Bean
    GoogleCredentials googleCredentials() {
        try {
            return (GoogleCredentials.fromStream(new ClassPathResource(fireBaseConfig).getInputStream()));
        } catch (IOException e) {
           throw new RuntimeException(e.getMessage());
        }
    }






}
