package org.example.designpattern.structural.bridge;

public class SmsSender implements MessageSender {
    @Override
    public String getChannelName() { return "短信"; }

    @Override
    public void doSend(String to, String content) {
        System.out.printf("    [短信渠道] 发送到 %s: %s%n", to, content);
    }
}
