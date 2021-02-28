package com.hypocrisy.maven.hypocrisyorder.mq.builder;

import com.hypocrisy.maven.hypocrisyorder.mq.name.OrderDelayedName;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/5 19:04
 * @Description:
 */
@Component
public class CustomBuilder {
    @Bean
    public CustomExchange orderDelayedExchange() {
        HashMap<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(OrderDelayedName.ORDER_EXCHANGE_DELAYED, "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue orderDelayedQueue() {
        return QueueBuilder.durable(OrderDelayedName.ORDER_QUEUE_DELAYED).build();
    }

    @Bean
    public Binding orderDelayedBinding(@Qualifier("orderDelayedQueue") Queue orderDelayedQueue, @Qualifier("orderDelayedExchange") CustomExchange orderDelayedExchange) {
        return BindingBuilder.bind(orderDelayedQueue).to(orderDelayedExchange).with("").noargs();
    }
}
