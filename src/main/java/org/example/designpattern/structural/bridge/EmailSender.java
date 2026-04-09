package org.example.designpattern.structural.bridge;

public class EmailSender implements MessageSender {
    @Override
    public String getChannelName() { return "邮件"; }

    @Override
    public void doSend(String to, String content) {
        System.out.printf("    [邮件渠道] 发送到 %s: %s%n", to, content);
    }
}
