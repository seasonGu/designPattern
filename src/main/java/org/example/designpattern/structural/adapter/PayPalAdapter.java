package org.example.designpattern.structural.adapter;

import java.math.BigDecimal;

/**
 * 适配器 —— PayPal 适配器
 * <p>
 * PayPal 需要两步调用（createPayment + executePayment），
 * 适配器将其封装为一步 pay()，对外隐藏复杂性。
 */
public class PayPalAdapter implements PayService {

    private final PayPalSDK payPalSDK;

    public PayPalAdapter(PayPalSDK payPalSDK) {
        this.payPalSDK = payPalSDK;
    }

    @Override
    public PayResult pay(String orderId, BigDecimal amount, String desc) {
        System.out.println("  [适配器] 转换参数: 我方格式 → PayPal 格式");

        // ① 调用 PayPal 第一步：创建支付
        String paymentId = payPalSDK.createPayment(
                amount.doubleValue(), "CNY", desc
        );

        // ② 调用 PayPal 第二步：执行支付
        boolean success = payPalSDK.executePayment(paymentId);

        // ③ 转换结果
        System.out.println("  [适配器] 转换结果: PayPal boolean → 我方 PayResult");
        return new PayResult(success, paymentId, success ? "Payment completed" : "Payment failed");
    }
}
