package com.rabbitmq.rabbitmqdemo.listener;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfiguration {

    @Bean
    Queue exampleQueue() {
        return QueueBuilder.durable("ExampleQueue")
                .autoDelete()
                .exclusive()
                .build();
    }
}
