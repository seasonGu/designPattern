package org.example.designpattern.creational.factorymethod;

/**
 * 【对比】简单工厂（不是 GoF 模式，但常拿来对比）
 * <p>
 * 问题：每次新增消息类型，都要修改这个 switch —— 违反「开闭原则」。
 * 工厂方法模式正是为了解决这个问题。
 */
public class SimpleMessageFactory {

    public static Message createMessage(String type) {
        return switch (type) {
            case "email" -> new EmailMessage();
            case "sms" -> new SmsMessage();
            case "wechat" -> new WechatMessage();
            // 如果要新增"钉钉消息"，就必须改这里 ❌
            default -> throw new IllegalArgumentException("未知消息类型: " + type);
        };
    }
}
