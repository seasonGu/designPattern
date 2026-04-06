package org.example.designpattern.structural.adapter;

import java.math.BigDecimal;

/**
 * 适配器 —— 微信支付适配器
 * <p>
 * 把我方参数转成 XML，调用微信 SDK，再把 XML 结果转回 PayResult。
 */
public class WechatPayAdapter implements PayService {

    private final WechatPaySDK wechatPaySDK;

    public WechatPayAdapter(WechatPaySDK wechatPaySDK) {
        this.wechatPaySDK = wechatPaySDK;
    }

    @Override
    public PayResult pay(String orderId, BigDecimal amount, String desc) {
        System.out.println("  [适配器] 转换参数: 我方格式 → 微信 XML 格式");

        // ① 转换参数：构建微信要求的 XML
        String xml = "<xml>"
                + "<out_trade_no>" + orderId + "</out_trade_no>"
                + "<total_fee>" + amount.multiply(BigDecimal.valueOf(100)).intValue() + "</total_fee>"
                + "<body>" + desc + "</body>"
                + "</xml>";

        // ② 调用第三方 SDK
        String response = wechatPaySDK.unifiedOrder(xml);

        // ③ 转换结果：解析微信返回的 XML → 我方 PayResult
        System.out.println("  [适配器] 转换结果: 微信 XML → 我方 PayResult");
        boolean success = response.contains("<result_code>SUCCESS</result_code>");
        String tradeNo = extractXmlValue(response, "prepay_id");
        return new PayResult(success, tradeNo, success ? "Success" : "Failed");
    }

    /** 简单的 XML 值提取（实际项目用 XML 解析库） */
    private String extractXmlValue(String xml, String tag) {
        String startTag = "<" + tag + ">";
        String endTag = "</" + tag + ">";
        int start = xml.indexOf(startTag) + startTag.length();
        int end = xml.indexOf(endTag);
        return (start > 0 && end > start) ? xml.substring(start, end) : "";
    }
}
