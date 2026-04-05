package org.example.designpattern.creational.factorymethod;

/**
 * 具体产品 —— 短信消息
 */
public class SmsMessage implements Message {

    @Override
    public void send(String to, String content) {
        System.out.printf("[短信] 发送到 %s，内容: %s%n", to, content);
    }
}
