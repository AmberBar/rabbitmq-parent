package com.amber.core.broker;

import com.amber.rabbitmq.api.Message;

public interface RabbitBroker {

    void sendMessages();

    /**
     * 发送那个快速消息
     * @param message
     */
    void rapidSend(Message message);

    /**
     * 确认消息
     * @param message
     */
    void confirmSend(Message message);

    /**
     * 可靠消息
     * @param message
     */
    void reliatSend(Message message);
}
