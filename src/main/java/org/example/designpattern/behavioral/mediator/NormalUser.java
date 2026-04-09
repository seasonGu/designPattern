package org.example.designpattern.behavioral.mediator;

/**
 * 具体同事类 —— 普通用户
 * <p>
 * 发送消息时委托给中介者，接收消息时被中介者回调。
 * 注意：普通用户完全不知道其他用户的存在！
 */
public class NormalUser extends User {

    public NormalUser(String name) {
        super(name);
    }

    @Override
    public void send(String message) {
        System.out.printf("    [%s] 发送消息: %s%n", name, message);
        // 不直接发给其他用户，而是交给中介者
        mediator.sendMessage(message, this);
    }

    @Override
    public void sendPrivate(String message, String receiverName) {
        System.out.printf("    [%s] 私聊 [%s]: %s%n", name, receiverName, message);
        mediator.sendPrivateMessage(message, this, receiverName);
    }

    @Override
    public void receive(String message, String from) {
        System.out.printf("    [%s] 收到来自 [%s] 的消息: %s%n", name, from, message);
    }
}
