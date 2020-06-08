package com.amber.rabbitmq.api;

import com.amber.rabbitmq.exception.MessageRuntimeException;

import java.util.List;

public interface MessageProducer {

    /**
     * message 消息的发送
     *
     * @param message
     * @throws MessageRuntimeException
     */
    void send(Message message) throws MessageRuntimeException;

    /**
     * 消息的批量发送
     *
     * @param messages
     * @throws MessageRuntimeException
     */
    void send(List<Message> messages) throws MessageRuntimeException;

    /**
     * 消息的发送 附带 sendcallback的回调执行业务逻辑处理
     *
     * @param message
     * @param sendCallback 回调执行业务逻辑处理
     * @throws MessageRuntimeException
     */
    void send(Message message, SendCallback sendCallback) throws MessageRuntimeException;


}
