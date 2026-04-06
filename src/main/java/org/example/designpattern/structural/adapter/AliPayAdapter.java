package org.example.designpattern.structural.adapter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 适配器 —— 支付宝适配器
 * <p>
 * 实现我方 PayService 接口，内部持有 AliPaySDK 的引用。
 * 负责：
 * 1. 把我方参数格式转换为支付宝的 Map 格式
 * 2. 调用支付宝 SDK
 * 3. 把支付宝的 Map 返回值转换为我方 PayResult
 */
public class AliPayAdapter implements PayService {

    private final AliPaySDK aliPaySDK;

    public AliPayAdapter(AliPaySDK aliPaySDK) {
        this.aliPaySDK = aliPaySDK;
    }

    @Override
    public PayResult pay(String orderId, BigDecimal amount, String desc) {
        System.out.println("  [适配器] 转换参数: 我方格式 → 支付宝 Map 格式");

        // ① 转换参数：我方参数 → 支付宝参数
        Map<String, String> params = new HashMap<>();
        params.put("out_trade_no", orderId);
        params.put("total_amount", amount.toString());
        params.put("subject", desc);
        params.put("product_code", "FAST_INSTANT_TRADE_PAY");

        // ② 调用第三方 SDK
        Map<String, String> response = aliPaySDK.sendPayment(params);

        // ③ 转换结果：支付宝返回值 → 我方 PayResult
        System.out.println("  [适配器] 转换结果: 支付宝 Map → 我方 PayResult");
        boolean success = "10000".equals(response.get("code"));
        return new PayResult(success, response.get("trade_no"), response.get("msg"));
    }
}
