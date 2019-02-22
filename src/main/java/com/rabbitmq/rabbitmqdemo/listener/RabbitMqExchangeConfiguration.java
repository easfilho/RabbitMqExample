package com.rabbitmq.rabbitmqdemo.listener;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqExchangeConfiguration {

    @Bean
    Exchange exampleExchange() {
        return new TopicExchange("ExampleExchange");
    }

    @Bean
    Exchange builderDirectExchange() {
        return ExchangeBuilder.directExchange("Example2ndExchange")
                .autoDelete()
                .internal()
                .build();
    }

    @Bean
    Exchange builderTopicExchange() {
        return ExchangeBuilder.topicExchange("TopicExchange")
                .autoDelete()
                .durable(true)
                .internal()
                .build();
    }

    @Bean
    Exchange builderFanoutExchange() {
        return ExchangeBuilder.fanoutExchange("FanoutExchange")
                .autoDelete()
                .durable(false)
                .internal()
                .build();
    }

    @Bean
    Exchange builderHeadersExchange() {
        return ExchangeBuilder.headersExchange("HeadersExchange")
                .internal()
                .durable(false)
                .ignoreDeclarationExceptions()
                .build();
    }

}
