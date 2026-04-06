package org.example.designpattern.structural.facade;

import java.util.UUID;

/**
 * 子系统③ —— 物流服务
 */
public class LogisticsService {

    public String createShipment(String orderId, String address) {
        String trackingNo = "SF" + UUID.randomUUID().toString().substring(0, 10).toUpperCase();
        System.out.printf("    [物流] 创建运单: %s%n", trackingNo);
        System.out.printf("    [物流] 收货地址: %s%n", address);
        return trackingNo;
    }

    public void cancelShipment(String trackingNo) {
        System.out.printf("    [物流] 取消运单: %s%n", trackingNo);
    }
}
