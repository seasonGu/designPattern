package org.example.designpattern.structural.bridge;

/**
 * 具体抽象 —— 特急消息
 * <p>
 * 最高优先级：加特急标记 + 通过所有可用渠道发送（突破单一渠道限制）。
 * 这里演示桥接模式的灵活性 —— 可以持有多个 sender。
 */
public class CriticalMessage extends Message {

    private final MessageSender[] backupSenders;

    public CriticalMessage(MessageSender primarySender, MessageSender... backupSenders) {
        super(primarySender);
        this.backupSenders = backupSenders;
    }

    @Override
    public void send(String to, String content) {
        String criticalContent = "【特急-立即处理】" + content;
        System.out.printf("  [特急消息] 通过所有渠道同时发送！%n");

        // 主渠道
        System.out.printf("    主渠道(%s):%n", sender.getChannelName());
        sender.doSend(to, criticalContent);

        // 备用渠道
        for (MessageSender backup : backupSenders) {
            System.out.printf("    备用渠道(%s):%n", backup.getChannelName());
            backup.doSend(to, criticalContent);
        }
    }
}
