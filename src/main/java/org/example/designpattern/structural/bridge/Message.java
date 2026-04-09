package org.example.designpattern.structural.bridge;

/**
 * 抽象维度（Abstraction）—— 消息
 * <p>
 * 持有 MessageSender 的引用 —— 这就是"桥"！
 * 消息类型（抽象维度）和发送渠道（实现维度）通过这个引用连接起来。
 * 两个维度可以独立变化、自由组合。
 */
public abstract class Message {

    /** 桥接的关键：持有实现维度的引用 */
    protected final MessageSender sender;

    public Message(MessageSender sender) {
        this.sender = sender;
    }

    /**
     * 发送消息 —— 子类实现不同的消息处理逻辑，
     * 但最终都通过 sender 来发送。
     */
    public abstract void send(String to, String content);
}
