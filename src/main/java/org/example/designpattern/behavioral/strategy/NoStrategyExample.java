package org.example.designpattern.behavioral.strategy;

import java.math.BigDecimal;

/**
 * 【反面教材】不用策略模式的写法
 * <p>
 * 问题：
 * 1. 每新增一种支付方式，就要改 checkout 方法，加一个 else if
 * 2. checkout 方法越来越臃肿
 * 3. 违反开闭原则和单一职责原则
 */
public class NoStrategyExample {

    public void checkout(String orderId, BigDecimal amount, String payType) {
        System.out.printf("订单 [%s]，金额 ¥%s%n", orderId, amount);

        if ("alipay".equals(payType)) {
            System.out.println("  调用支付宝SDK...");
            // 支付宝的一堆逻辑...
        } else if ("wechat".equals(payType)) {
            System.out.println("  调用微信SDK...");
            // 微信的一堆逻辑...
        } else if ("creditcard".equals(payType)) {
            System.out.println("  调用银联接口...");
            // 信用卡的一堆逻辑...
        }
        // 新增钉钉支付？还得在这里加 else if... ❌
    }
}
