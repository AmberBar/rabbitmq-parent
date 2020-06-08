package com.amber.rabbitmq.api;

/**
 * 消息类型，主要提供三种消息类型
 * 1. 迅速消息 不需要保障消息的可靠性，也不需要做confirm 确认
 * 2. 确认消息 不需要保障消息的可靠性，但是会做消息的confirm 确认
 * 3. 可靠性消息 一定要保障消息的百分之百可靠性投递，不允许有任何消息的丢失
 * @Author: Amber
 */
public class MessageType {


    /**
     * 迅速消息
     * @Desc: 不需要保障消息的可靠性，也不需要做confirm 确认
     */
    public final static String RAPID = "0";

    /**
     * 确认消息
     * @Desc: 不需要保障消息的可靠性，但是会做消息的confirm 确认
     */
    public final static String CONFIRM = "1";

    /**
     * 可靠性消息
     * @Desc: 一定要保障消息的百分之百可靠性投递，不允许有任何消息的丢失
     * @PS: 保障数据库和所发的消息是原子性的 （最终一致性）
     */
    public final static String RELIANT = "2";
}
