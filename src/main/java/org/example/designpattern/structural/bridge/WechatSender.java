package org.example.designpattern.structural.bridge;

public class WechatSender implements MessageSender {
    @Override
    public String getChannelName() { return "微信"; }

    @Override
    public void doSend(String to, String content) {
        System.out.printf("    [微信渠道] 推送到 %s: %s%n", to, content);
    }
}
