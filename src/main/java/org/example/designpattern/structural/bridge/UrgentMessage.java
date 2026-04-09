package org.example.designpattern.structural.bridge;

/**
 * 具体抽象 —— 加急消息
 * <p>
 * 在普通消息基础上增加了「加急标记」和「重复发送」逻辑。
 */
public class UrgentMessage extends Message {

    public UrgentMessage(MessageSender sender) {
        super(sender);
    }

    @Override
    public void send(String to, String content) {
        String urgentContent = "【加急】" + content;
        System.out.printf("  [加急消息] 通过%s发送（发送2次确保送达）%n", sender.getChannelName());
        sender.doSend(to, urgentContent);
        sender.doSend(to, urgentContent + "（第二次提醒）");
    }
}
