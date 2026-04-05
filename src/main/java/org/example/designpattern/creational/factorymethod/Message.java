package org.example.designpattern.creational.factorymethod;

/**
 * 抽象产品 —— 消息接口
 * <p>
 * 定义所有消息类型的统一行为。
 * 调用方只依赖这个接口，不关心具体是邮件还是短信。
 */
public interface Message {

    /**
     * 发送消息
     *
     * @param to      接收人
     * @param content 消息内容
     */
    void send(String to, String content);
}
