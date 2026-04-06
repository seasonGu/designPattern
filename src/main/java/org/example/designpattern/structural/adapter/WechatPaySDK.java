package org.example.designpattern.structural.adapter;

import java.util.UUID;

/**
 * 第三方 SDK —— 微信支付
 * <p>
 * 微信的接口又是另一种风格：
 * - 方法名：unifiedOrder（统一下单）
 * - 参数：用 XML 格式的字符串
 * - 返回值：也是 XML 字符串
 */
public class WechatPaySDK {

    public String unifiedOrder(String xmlRequest) {
        System.out.println("    [微信SDK] 接收XML请求: " + xmlRequest);
        System.out.println("    [微信SDK] 调用微信支付网关...");
        System.out.println("    [微信SDK] 支付处理完成");

        String tradeNo = "WX" + UUID.randomUUID().toString().substring(0, 8);
        return "<xml><return_code>SUCCESS</return_code>"
                + "<result_code>SUCCESS</result_code>"
                + "<prepay_id>" + tradeNo + "</prepay_id></xml>";
    }
}
