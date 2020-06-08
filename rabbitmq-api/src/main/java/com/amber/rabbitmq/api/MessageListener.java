package com.amber.rabbitmq.api;

public interface MessageListener {

    void onListener(Message message);

}
