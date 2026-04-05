package org.example.designpattern.creational.factorymethod;

/**
 * 具体产品 —— 微信推送消息
 */
public class WechatMessage implements Message {

    @Override
    public void send(String to, String content) {
        System.out.printf("[微信] 推送到 %s，内容: %s%n", to, content);
    }
}
