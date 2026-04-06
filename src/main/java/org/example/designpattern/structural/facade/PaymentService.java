package org.example.designpattern.structural.facade;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * 子系统② —— 支付服务
 */
public class PaymentService {

    public String createPayment(String orderId, BigDecimal amount) {
        String paymentId = "PAY-" + UUID.randomUUID().toString().substring(0, 8);
        System.out.printf("    [支付] 创建支付单: %s，金额 ¥%s%n", paymentId, amount);
        return paymentId;
    }

    public boolean executePayment(String paymentId) {
        System.out.printf("    [支付] 执行扣款: %s ...%n", paymentId);
        System.out.printf("    [支付] 扣款成功 ✅%n");
        return true;
    }

    public void refund(String paymentId) {
        System.out.printf("    [支付] 退款: %s%n", paymentId);
    }
}
