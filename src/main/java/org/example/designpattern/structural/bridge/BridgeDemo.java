package org.example.designpattern.structural.bridge;

/**
 * 桥接模式 —— 演示
 */
public class BridgeDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  问题：两个维度的组合导致类爆炸");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""
                用继承方式（❌ 类爆炸）：
                  NormalEmailMessage, NormalSmsMessage, NormalWechatMessage,
                  UrgentEmailMessage, UrgentSmsMessage, UrgentWechatMessage,
                  CriticalEmailMessage, CriticalSmsMessage, CriticalWechatMessage
                  → 3类型 × 3渠道 = 9个类！新增一个渠道要加3个类！

                用桥接方式（✅ 组合代替继承）：
                  消息类型: NormalMessage, UrgentMessage, CriticalMessage (3个)
                  发送渠道: EmailSender, SmsSender, WechatSender              (3个)
                  → 3 + 3 = 6个类，自由组合！新增一个渠道只加1个类！
                """);

        // 创建发送渠道（实现维度）
        MessageSender email = new EmailSender();
        MessageSender sms = new SmsSender();
        MessageSender wechat = new WechatSender();

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 普通消息 × 3种渠道");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        new NormalMessage(email).send("zhangsan@qq.com", "明天下午3点开会");
        System.out.println();
        new NormalMessage(sms).send("13800001111", "明天下午3点开会");
        System.out.println();
        new NormalMessage(wechat).send("wx_zhangsan", "明天下午3点开会");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 加急消息 × 不同渠道");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        new UrgentMessage(sms).send("13800001111", "服务器CPU超过90%");
        System.out.println();
        new UrgentMessage(email).send("ops@company.com", "服务器CPU超过90%");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 特急消息 —— 所有渠道同时发送");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        new CriticalMessage(sms, email, wechat)
                .send("张三", "生产数据库宕机！需要立即处理！");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 运行时自由组合 —— 桥接的灵活性");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 模拟根据配置动态选择
        String urgencyLevel = "urgent";
        String channel = "wechat";

        MessageSender sender = switch (channel) {
            case "email" -> new EmailSender();
            case "sms" -> new SmsSender();
            case "wechat" -> new WechatSender();
            default -> throw new IllegalArgumentException("未知渠道: " + channel);
        };

        Message message = switch (urgencyLevel) {
            case "normal" -> new NormalMessage(sender);
            case "urgent" -> new UrgentMessage(sender);
            case "critical" -> new CriticalMessage(sender, new SmsSender());
            default -> throw new IllegalArgumentException("未知级别: " + urgencyLevel);
        };

        System.out.printf("  动态组合: %s + %s%n", urgencyLevel, channel);
        message.send("运维组", "请检查日志");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                桥接模式的"桥"在哪里？

                 抽象维度（消息类型）          实现维度（发送渠道）
                ┌──────────────┐           ┌──────────────┐
                │ NormalMessage│──sender──►│ EmailSender  │
                │ UrgentMessage│    ↑      │ SmsSender    │
                │ CriticalMsg  │   桥！     │ WechatSender │
                └──────────────┘           └──────────────┘
                  可独立扩展                   可独立扩展

                桥 = Message 中的 sender 字段
                通过组合（而非继承）连接两个维度

                扩展时：
                  新增消息类型 → 只加一个 Message 子类
                  新增发送渠道 → 只加一个 Sender 实现
                  互不影响 ✅
                """);
    }
}
