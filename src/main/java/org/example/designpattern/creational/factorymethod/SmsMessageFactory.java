package org.example.designpattern.creational.factorymethod;

/**
 * 具体工厂 —— 短信消息工厂
 */
public class SmsMessageFactory implements MessageFactory {

    @Override
    public Message createMessage() {
        System.out.println("  → 短信工厂：创建短信消息实例");
        return new SmsMessage();
    }
}
