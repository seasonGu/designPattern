package org.example.designpattern.behavioral.strategy;

import java.math.BigDecimal;

/**
 * 策略模式 —— 演示
 */
public class StrategyDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════");
        System.out.println("  1. 基本用法 —— 构造时选择策略");
        System.out.println("═══════════════════════════════════════════");

        // 用户选择了支付宝
        OrderService order1 = new OrderService(new AliPayStrategy("zhangsan@alipay.com"));
        order1.checkout("ORD-001", new BigDecimal("99.90"));

        System.out.println();

        // 用户选择了微信支付
        OrderService order2 = new OrderService(new WechatPayStrategy("wx_openid_123"));
        order2.checkout("ORD-002", new BigDecimal("258.00"));

        System.out.println();

        // 用户选择了信用卡
        OrderService order3 = new OrderService(new CreditCardStrategy("6222021234567890", "张三"));
        order3.checkout("ORD-003", new BigDecimal("1299.00"));

        System.out.println();
        System.out.println("═══════════════════════════════════════════");
        System.out.println("  2. 运行时动态切换策略");
        System.out.println("═══════════════════════════════════════════");

        OrderService orderService = new OrderService(new AliPayStrategy("lisi@alipay.com"));
        System.out.println("用户第一次选了支付宝：");
        orderService.checkout("ORD-004", new BigDecimal("66.60"));

        System.out.println();
        System.out.println("用户改主意了，换成微信支付：");
        // 运行时切换策略，不需要改 OrderService 的任何代码
        orderService.setPaymentStrategy(new WechatPayStrategy("wx_openid_456"));
        orderService.checkout("ORD-004", new BigDecimal("66.60"));

        System.out.println();
        System.out.println("═══════════════════════════════════════════");
        System.out.println("  3. 策略 + 工厂（实际项目中常见的组合）");
        System.out.println("═══════════════════════════════════════════");

        // 模拟前端传过来的支付方式
        String[] userChoices = {"alipay", "wechat", "creditcard"};

        for (String choice : userChoices) {
            PaymentStrategy strategy = createStrategy(choice);
            OrderService os = new OrderService(strategy);
            os.checkout("ORD-005", new BigDecimal("199.00"));
            System.out.println();
        }
    }

    /**
     * 简单工厂 + 策略模式的组合
     * <p>
     * 实际项目中这个方法通常在 Spring 里用 Map 注入来替代
     */
    private static PaymentStrategy createStrategy(String payType) {
        return switch (payType) {
            case "alipay" -> new AliPayStrategy("default@alipay.com");
            case "wechat" -> new WechatPayStrategy("wx_default");
            case "creditcard" -> new CreditCardStrategy("6222020000000000", "默认用户");
            default -> throw new IllegalArgumentException("不支持的支付方式: " + payType);
        };
    }
}
