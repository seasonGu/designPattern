package org.example.designpattern.creational.factorymethod;

/**
 * 具体工厂 —— 微信消息工厂
 */
public class WechatMessageFactory implements MessageFactory {

    @Override
    public Message createMessage() {
        System.out.println("  → 微信工厂：创建微信推送消息实例");
        return new WechatMessage();
    }
}
