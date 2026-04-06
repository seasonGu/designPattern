package org.example.designpattern.structural.adapter;

import java.math.BigDecimal;

/**
 * 目标接口 —— 我方系统定义的统一支付接口
 * <p>
 * 我方所有业务代码都依赖这个接口。
 * 第三方 SDK 的接口和我们不同，所以需要适配器来"翻译"。
 */
public interface PayService {

    /**
     * 统一支付方法
     *
     * @param orderId 订单号
     * @param amount  金额
     * @param desc    描述
     * @return 支付结果
     */
    PayResult pay(String orderId, BigDecimal amount, String desc);
}
