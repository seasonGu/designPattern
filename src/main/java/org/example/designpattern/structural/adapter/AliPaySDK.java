package org.example.designpattern.structural.adapter;

import java.util.Map;
import java.util.UUID;

/**
 * 第三方 SDK —— 支付宝
 * <p>
 * 这是支付宝提供的原始 SDK，接口格式和我方系统完全不同：
 * - 方法名不同：sendPayment（不是 pay）
 * - 参数不同：用 Map 传参（不是独立参数）
 * - 返回值不同：返回 Map（不是 PayResult）
 * <p>
 * 我们不能修改第三方 SDK 的代码！
 */
public class AliPaySDK {

    public Map<String, String> sendPayment(Map<String, String> params) {
        System.out.println("    [支付宝SDK] 接收参数: " + params);
        System.out.println("    [支付宝SDK] 调用支付宝网关...");
        System.out.println("    [支付宝SDK] 支付处理完成");

        return Map.of(
                "code", "10000",
                "msg", "Success",
                "trade_no", "ALI" + UUID.randomUUID().toString().substring(0, 8),
                "out_trade_no", params.getOrDefault("out_trade_no", "")
        );
    }
}
