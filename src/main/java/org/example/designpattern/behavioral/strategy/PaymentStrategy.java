package org.example.designpattern.behavioral.strategy;

import java.math.BigDecimal;

/**
 * 策略接口 —— 支付策略
 * <p>
 * 所有支付方式都实现这个接口，保证可以互相替换。
 */
public interface PaymentStrategy {

    /**
     * 获取支付方式名称
     */
    String getName();

    /**
     * 执行支付
     *
     * @param amount 支付金额
     * @return true=支付成功, false=支付失败
     */
    boolean pay(BigDecimal amount);
}
