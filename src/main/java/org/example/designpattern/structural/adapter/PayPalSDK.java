package org.example.designpattern.structural.adapter;

import java.util.UUID;

/**
 * 第三方 SDK —— PayPal
 * <p>
 * PayPal 又是另一种风格：
 * - 方法名：createPayment + executePayment（两步走）
 * - 参数：独立参数但类型不同（金额用 double）
 * - 返回值：自定义对象
 */
public class PayPalSDK {

    public String createPayment(double amount, String currency, String description) {
        System.out.printf("    [PayPal SDK] Creating payment: %.2f %s - %s%n",
                amount, currency, description);
        return "PAL" + UUID.randomUUID().toString().substring(0, 8);
    }

    public boolean executePayment(String paymentId) {
        System.out.printf("    [PayPal SDK] Executing payment: %s%n", paymentId);
        System.out.println("    [PayPal SDK] Payment executed successfully");
        return true;
    }
}
