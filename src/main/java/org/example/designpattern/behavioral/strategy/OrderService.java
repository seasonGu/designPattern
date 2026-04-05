package org.example.designpattern.behavioral.strategy;

import java.math.BigDecimal;

/**
 * 上下文（Context）—— 订单服务
 * <p>
 * 持有一个策略引用，将支付行为委托给策略对象。
 * OrderService 不关心具体的支付逻辑，只管调用策略。
 */
public class OrderService {

    private PaymentStrategy paymentStrategy;

    /**
     * 构造时指定策略
     */
    public OrderService(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * 运行时动态切换策略 —— 这就是策略模式的核心价值
     */
    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    /**
     * 下单并支付
     */
    public void checkout(String orderId, BigDecimal amount) {
        System.out.printf("订单 [%s]，金额 ¥%s，使用【%s】支付%n",
                orderId, amount, paymentStrategy.getName());
        boolean success = paymentStrategy.pay(amount);
        if (success) {
            System.out.printf("订单 [%s] 支付完成 ✅%n", orderId);
        } else {
            System.out.printf("订单 [%s] 支付失败 ❌%n", orderId);
        }
    }
}
