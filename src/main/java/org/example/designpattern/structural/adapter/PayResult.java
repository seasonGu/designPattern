package org.example.designpattern.structural.adapter;

/**
 * 我方统一的支付结果
 */
public record PayResult(
        boolean success,
        String tradeNo,
        String message
) {
    @Override
    public String toString() {
        return String.format("PayResult{%s, tradeNo='%s', msg='%s'}",
                success ? "成功" : "失败", tradeNo, message);
    }
}
