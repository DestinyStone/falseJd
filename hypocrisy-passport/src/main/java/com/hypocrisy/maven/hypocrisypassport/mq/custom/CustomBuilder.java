package com.hypocrisy.maven.hypocrisypassport.mq.custom;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/17 20:24
 * @Description:
 */
@Configuration
public class CustomBuilder {

    @Bean
    public Exchange verityExchange() {
        return ExchangeBuilder.directExchange(VerityMQName.EXCHANGE_NAME).build();
    }
    @Bean
    public Queue emailQueue() {
        return QueueBuilder.durable(VerityMQName.EMAIL_QUEUE).build();
    }
    @Bean
    public Queue phoneQueue() {
        return QueueBuilder.durable(VerityMQName.PHONE_QUEUE).build();
    }
    @Bean
    public Binding bindingEmailQueue(@Qualifier("emailQueue") Queue emailQueue, @Qualifier("verityExchange") Exchange exchange) {
        return BindingBuilder.bind(emailQueue).to(exchange).with(VerityMQName.EMAIL_KEY).noargs();
    }
    @Bean
    public Binding bindingphoneQueue(@Qualifier("phoneQueue") Queue phoneQueue, @Qualifier("verityExchange") Exchange exchange) {
        return BindingBuilder.bind(phoneQueue).to(exchange).with(VerityMQName.PHONE_KEY).noargs();
    }

}
