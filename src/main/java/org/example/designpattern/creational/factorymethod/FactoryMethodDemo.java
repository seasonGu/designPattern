package org.example.designpattern.creational.factorymethod;

/**
 * 工厂方法模式 —— 演示
 */
public class FactoryMethodDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════");
        System.out.println("  1. 简单工厂（对比，非 GoF 模式）");
        System.out.println("═══════════════════════════════════════");
        // 调用方需要传类型字符串，工厂内部用 switch 判断
        Message email1 = SimpleMessageFactory.createMessage("email");
        email1.send("zhangsan@qq.com", "你好，这是简单工厂创建的邮件");

        Message sms1 = SimpleMessageFactory.createMessage("sms");
        sms1.send("13800138000", "你好，这是简单工厂创建的短信");

        System.out.println();
        System.out.println("═══════════════════════════════════════");
        System.out.println("  2. 工厂方法模式（GoF）");
        System.out.println("═══════════════════════════════════════");
        // 每种消息有自己的工厂，调用方只依赖 MessageFactory 接口
        MessageFactory emailFactory = new EmailMessageFactory();
        emailFactory.sendMessage("zhangsan@qq.com", "你好，这是工厂方法创建的邮件");

        System.out.println();

        MessageFactory smsFactory = new SmsMessageFactory();
        smsFactory.sendMessage("13800138000", "你好，这是工厂方法创建的短信");

        System.out.println();

        MessageFactory wechatFactory = new WechatMessageFactory();
        wechatFactory.sendMessage("wx_zhangsan", "你好，这是工厂方法创建的微信推送");

        System.out.println();
        System.out.println("═══════════════════════════════════════");
        System.out.println("  3. 工厂方法的威力 —— 不改已有代码就能扩展");
        System.out.println("═══════════════════════════════════════");
        System.out.println("如果要新增「钉钉消息」，只需要：");
        System.out.println("  ① 新建 DingTalkMessage implements Message");
        System.out.println("  ② 新建 DingTalkMessageFactory implements MessageFactory");
        System.out.println("  不需要修改任何已有的类！✅ 符合开闭原则");

        System.out.println();
        System.out.println("═══════════════════════════════════════");
        System.out.println("  4. 多态的力量 —— 调用方只依赖接口");
        System.out.println("═══════════════════════════════════════");
        // 实际项目中，工厂可以通过配置/参数/注入来决定
        MessageFactory[] factories = {
                new EmailMessageFactory(),
                new SmsMessageFactory(),
                new WechatMessageFactory()
        };

        for (MessageFactory factory : factories) {
            // 调用方完全不知道具体是什么消息类型
            factory.sendMessage("user_001", "系统通知：您的订单已发货");
            System.out.println();
        }
    }
}
