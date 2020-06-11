package com.amber.core.broker.impl;

import com.amber.core.broker.AsyncBaseQueue;
import com.amber.core.broker.RabbitBroker;
import com.amber.rabbitmq.api.Message;
import com.amber.rabbitmq.api.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: Amber
 */
@Slf4j
public class RabbitBrokerImpl implements RabbitBroker {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessages() {

    }

    @Override
    public void rapidSend(Message message) {
        message.setMessageType(MessageType.RAPID);
        senKernel(message);
    }

    @Override
    public void confirmSend(Message message) {

    }

    @Override
    public void reliatSend(Message message) {

    }

    /**
     * 发送消息到rabbitmq
     *
     * @param message
     */
    public void senKernel(Message message) {
        AsyncBaseQueue.submit(() -> {
            CorrelationData correlationData = new CorrelationData(message.getMessageId());
            String exchange = message.getExchange();
            String routingKey = message.getRoutingKey();
            // 发送消息到MQ
            rabbitTemplate.convertAndSend(exchange, routingKey, message, correlationData);
        });

        log.info("#RabbitBrokerImpl.sendKernel# send to rabbitMq, messageId : {}", message.getMessageId());
    }
}
