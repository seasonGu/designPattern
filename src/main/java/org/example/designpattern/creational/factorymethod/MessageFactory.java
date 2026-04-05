package org.example.designpattern.creational.factorymethod;

/**
 * 抽象工厂（Creator）—— 消息工厂接口
 * <p>
 * 这是工厂方法模式的核心：
 * - 定义了创建产品的抽象方法 {@link #createMessage()}
 * - 具体创建哪种消息，由子类（具体工厂）决定
 * - 还可以提供通用的业务方法，如 {@link #sendMessage(String, String)}
 */
public interface MessageFactory {

    /**
     * 工厂方法 —— 由子类实现，决定创建哪种消息
     */
    Message createMessage();

    /**
     * 业务方法 —— 使用工厂方法创建的产品来完成业务
     * <p>
     * 注意：调用方不需要知道具体创建了什么消息，
     *       只管调用 sendMessage 即可。
     */
    default void sendMessage(String to, String content) {
        Message message = createMessage();
        message.send(to, content);
    }
}
