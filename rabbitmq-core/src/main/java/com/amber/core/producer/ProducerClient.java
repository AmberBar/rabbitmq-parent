package com.amber.core.producer;

import com.amber.rabbitmq.api.Message;
import com.amber.rabbitmq.api.MessageProducer;
import com.amber.rabbitmq.api.MessageType;
import com.amber.rabbitmq.api.SendCallback;
import com.amber.rabbitmq.exception.MessageRuntimeException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.amber.core.broker.RabbitBroker;
import java.util.List;

/**
 * 发送消息的实现类
 * 生产者
 * @Author: Amber
 */
@Component
public class ProducerClient implements MessageProducer {

    @Autowired
    RabbitBroker rabbitBroker;

    @Override
    public void send(Message message) throws MessageRuntimeException {
        Preconditions.checkNotNull(message.getExchange()); // 谷歌的工具包
        String messageType = message.getMessageType();
        switch (messageType) {
            case MessageType.RAPID:
                rabbitBroker.rapidSend(message);
                break;
            case MessageType.CONFIRM:
                rabbitBroker.confirmSend(message);
                break;
            case MessageType.RELIANT:
                rabbitBroker.reliatSend(message);
                break;
        }
    }

    @Override
    public void send(List<Message> messages) throws MessageRuntimeException {

    }

    @Override
    public void send(Message message, SendCallback sendCallback) throws MessageRuntimeException {

    }
}
