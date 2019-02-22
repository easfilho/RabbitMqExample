package com.rabbitmq.rabbitmqdemo.listener;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqMessageListenerBean {

    @Bean
    RabbitMqMessageListener rabbitMqMessageListener() {
        return new RabbitMqMessageListener();
    }
}
