package com.amber.core.broker;

import com.amber.rabbitmq.api.Message;
import com.amber.rabbitmq.api.MessageType;
import com.amber.rabbitmq.exception.MessageRuntimeException;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import java.util.Map;

/**
 * @Author: Amber
 * RabbitTemplate的池化，提高性能
 */
@Component
@Slf4j
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback {

    private Map<String, RabbitTemplate> rabbitMaps = Maps.newConcurrentMap();

    @Autowired
    private ConnectionFactory connectionFactory;

    public RabbitTemplate getTemplate(Message message) throws MessageRuntimeException {
        String exchange = message.getExchange();
        String messageType = message.getMessageType();
        String routingKey = message.getRoutingKey();
        Preconditions.checkNotNull(exchange, "exchange cannot be empty");
        Preconditions.checkNotNull(messageType, "messageType cannot be empty");
        Preconditions.checkNotNull(routingKey, "routingKey cannot be empty");
        RabbitTemplate rabbitTemplate = rabbitMaps.get(exchange);
        if (null != rabbitTemplate) {
            return rabbitTemplate;
        }
        // RabbitTemplate为null,需要新创建一个Template
        RabbitTemplate newRabbitTemplate = new RabbitTemplate(connectionFactory);
        newRabbitTemplate.setExchange(exchange);
        newRabbitTemplate.setRoutingKey(routingKey);
        // TODO ?
        newRabbitTemplate.setRetryTemplate(new RetryTemplate());
        // 不是迅速消息都需要设置应答
        if (!MessageType.RAPID.equals(messageType)) {
            newRabbitTemplate.setConfirmCallback(this);
        }
        rabbitMaps.put(exchange, newRabbitTemplate);
        log.info("#RabbitTemplateContainer.getTemplate# rabbitTemplate not exist,create one {}", exchange);

        return rabbitMaps.get(exchange);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        String messageId = correlationData.getId();
        // 成功返回
        if (ack) {
            log.info("send message is ok,confirm messageId : {}, sendTime : {}",messageId );
        } else {
            log.error("send message is fail confirm messageId : {}, sendTime : {}",messageId);
        }
    }
}
