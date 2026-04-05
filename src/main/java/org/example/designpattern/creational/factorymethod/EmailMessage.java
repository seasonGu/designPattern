package org.example.designpattern.creational.factorymethod;

/**
 * 具体产品 —— 邮件消息
 */
public class EmailMessage implements Message {

    @Override
    public void send(String to, String content) {
        System.out.printf("[邮件] 发送到 %s，内容: %s%n", to, content);
    }
}
