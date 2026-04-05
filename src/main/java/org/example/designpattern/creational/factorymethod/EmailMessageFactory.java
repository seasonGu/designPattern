package org.example.designpattern.creational.factorymethod;

/**
 * 具体工厂 —— 邮件消息工厂
 * <p>
 * 只负责创建 EmailMessage，职责单一。
 */
public class EmailMessageFactory implements MessageFactory {

    @Override
    public Message createMessage() {
        System.out.println("  → 邮件工厂：创建邮件消息实例");
        return new EmailMessage();
    }
}
