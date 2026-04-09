package org.example.designpattern.structural.bridge;

/**
 * 具体抽象 —— 普通消息
 */
public class NormalMessage extends Message {

    public NormalMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String to, String content) {
        System.out.printf("  [普通消息] 通过%s发送%n", sender.getChannelName());
        sender.doSend(to, content);
    }
}
