package com.amber.rabbitmq.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 消息
 *
 * @author amber
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {


    private static final long serialVersionUID = 3913836033001036223L;

    /**
     * 消息的唯一ID
     */
    private String messageId;

    /**
     * 消息的主题 这里的topic实际上还可以再对exchange进行封装
     * TODO
     */
    private String topic;

    /**
     * 消息的路由规则
     *
     * @desc: . * #
     */
    private String routingKey = "";

    /**
     * 消息的附加属性
     */
    private Map<String, Object> attributes = new HashMap<>();

    /**
     * 延迟消息的参数配置
     */
    private int delayMills;

    /**
     * 消息类型
     *
     * @Default: 默认位确认类型
     */
    private String messageType = MessageType.CONFIRM;

}
