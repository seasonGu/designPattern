package org.example.designpattern.behavioral.strategy;

import java.math.BigDecimal;

/**
 * 具体策略 —— 微信支付
 */
public class WechatPayStrategy implements PaymentStrategy {

    private final String openId;

    public WechatPayStrategy(String openId) {
        this.openId = openId;
    }

    @Override
    public String getName() {
        return "微信支付";
    }

    @Override
    public boolean pay(BigDecimal amount) {
        System.out.printf("  [微信支付] 用户 %s 支付 ¥%s ...%n", openId, amount);
        System.out.println("  [微信支付] 调用微信SDK → 唤起支付 → 输入密码 → 支付成功 ✅");
        return true;
    }
}
