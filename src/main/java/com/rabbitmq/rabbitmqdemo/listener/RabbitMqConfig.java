package com.rabbitmq.rabbitmqdemo.listener;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Bean
    Queue queue() {
        return new Queue("MyQueue", true);
    }

    @Bean
    Exchange exchange() {
        return ExchangeBuilder.topicExchange("MyTopicExchange")
                .durable(true)
                .build();
    }

    @Bean
    Binding binding() {
//        return new Binding("MyQueue",
//                Binding.DestinationType.QUEUE,
//                "MyTopicExchange",
//                "topic",
//                null);

        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with("topic")
                .noargs();
    }

    @Autowired
    private RabbitMqMessageListener rabbitMqMessageListener;

    @Bean
    ConnectionFactory connectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory("localhost");
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        return cachingConnectionFactory;
    }

    @Bean
    MessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer simpleMessageListenerContainer = new SimpleMessageListenerContainer();
        simpleMessageListenerContainer.setConnectionFactory(connectionFactory());
        simpleMessageListenerContainer.setQueues(queue());
        simpleMessageListenerContainer.setMessageListener(rabbitMqMessageListener);
        return simpleMessageListenerContainer;
    }
}
