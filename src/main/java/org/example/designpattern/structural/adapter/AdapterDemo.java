package org.example.designpattern.structural.adapter;

import java.math.BigDecimal;

/**
 * 适配器模式 —— 演示
 */
public class AdapterDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  问题：三家 SDK 接口完全不同");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""
                支付宝: sendPayment(Map<String, String> params)     → Map<String, String>
                微信:   unifiedOrder(String xml)                    → String (XML)
                PayPal: createPayment(double, String, String)       → String
                        executePayment(String paymentId)            → boolean

                我方系统: pay(String orderId, BigDecimal amount, String desc) → PayResult

                接口名不同、参数类型不同、返回值不同、调用步骤都不同！
                解决方案：为每个 SDK 写一个适配器，统一转换为我方接口
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 支付宝适配器（Map 参数 → 统一接口）");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        PayService aliPay = new AliPayAdapter(new AliPaySDK());
        PayResult result1 = aliPay.pay("ORD-001", new BigDecimal("99.90"), "购买iPhone手机壳");
        System.out.println("  最终结果: " + result1);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 微信适配器（XML 格式 → 统一接口）");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        PayService wechatPay = new WechatPayAdapter(new WechatPaySDK());
        PayResult result2 = wechatPay.pay("ORD-002", new BigDecimal("258.00"), "购买耳机");
        System.out.println("  最终结果: " + result2);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. PayPal 适配器（两步调用 → 统一一步）");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        PayService payPal = new PayPalAdapter(new PayPalSDK());
        PayResult result3 = payPal.pay("ORD-003", new BigDecimal("1299.00"), "Buy Laptop Stand");
        System.out.println("  最终结果: " + result3);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 统一接口的力量 —— 业务代码完全一致");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 业务代码只依赖 PayService，完全不知道背后是哪家SDK
        PayService[] payServices = {
                new AliPayAdapter(new AliPaySDK()),
                new WechatPayAdapter(new WechatPaySDK()),
                new PayPalAdapter(new PayPalSDK())
        };

        for (PayService service : payServices) {
            // 调用方式完全一样！
            PayResult result = service.pay("ORD-004", new BigDecimal("66.60"), "测试商品");
            System.out.println("  结果: " + result);
            System.out.println();
        }
    }
}
