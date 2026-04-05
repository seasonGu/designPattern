package org.example.designpattern.behavioral.strategy;

import java.math.BigDecimal;

/**
 * 具体策略 —— 信用卡支付
 */
public class CreditCardStrategy implements PaymentStrategy {

    private final String cardNumber;
    private final String cardHolder;

    public CreditCardStrategy(String cardNumber, String cardHolder) {
        this.cardNumber = cardNumber;
        this.cardHolder = cardHolder;
    }

    @Override
    public String getName() {
        return "信用卡";
    }

    @Override
    public boolean pay(BigDecimal amount) {
        // 隐藏卡号中间部分
        String maskedCard = cardNumber.substring(0, 4) + " **** **** "
                + cardNumber.substring(cardNumber.length() - 4);
        System.out.printf("  [信用卡] %s 的卡 %s 支付 ¥%s ...%n", cardHolder, maskedCard, amount);
        System.out.println("  [信用卡] 发送到银联 → 验证 → 扣款 → 支付成功 ✅");
        return true;
    }
}
