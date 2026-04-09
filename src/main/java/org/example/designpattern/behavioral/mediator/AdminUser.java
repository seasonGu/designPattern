package org.example.designpattern.behavioral.mediator;

/**
 * 具体同事类 —— 管理员用户
 * <p>
 * 管理员可以发送公告（所有人都能看到，包括自己）。
 * 普通消息的行为和 NormalUser 一样。
 */
public class AdminUser extends User {

    public AdminUser(String name) {
        super(name);
    }

    @Override
    public void send(String message) {
        System.out.printf("    [管理员 %s] 发送消息: %s%n", name, message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void sendPrivate(String message, String receiverName) {
        System.out.printf("    [管理员 %s] 私聊 [%s]: %s%n", name, receiverName, message);
        mediator.sendPrivateMessage(message, this, receiverName);
    }

    /** 管理员特有功能 —— 发公告 */
    public void sendAnnouncement(String message) {
        System.out.printf("    [管理员 %s] 发布公告: %s%n", name, message);
        mediator.sendAnnouncement(message, this);
    }

    @Override
    public void receive(String message, String from) {
        System.out.printf("    [管理员 %s] 收到来自 [%s] 的消息: %s%n", name, from, message);
    }
}
