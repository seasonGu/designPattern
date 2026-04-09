package org.example.designpattern.structural.bridge;

/**
 * 实现维度（Implementor）—— 发送渠道接口
 * <p>
 * 定义发送消息的底层实现。
 * 抽象维度（Message）通过持有这个接口的引用来"桥接"。
 */
public interface MessageSender {

    /**
     * 获取渠道名称
     */
    String getChannelName();

    /**
     * 实际发送
     *
     * @param to      接收人
     * @param content 消息内容
     */
    void doSend(String to, String content);
}
