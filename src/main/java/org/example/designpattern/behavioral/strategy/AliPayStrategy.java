package org.example.designpattern.behavioral.strategy;

import java.math.BigDecimal;

/**
 * 具体策略 —— 支付宝支付
 */
public class AliPayStrategy implements PaymentStrategy {

    private final String account;

    public AliPayStrategy(String account) {
        this.account = account;
    }

    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.printf("  [支付宝] 账户 %s 支付 ¥%s ...%n", account, amount);
        System.out.println("  [支付宝] 调用支付宝SDK → 扫码 → 确认 → 支付成功 ✅");
        return true;
    }
}
