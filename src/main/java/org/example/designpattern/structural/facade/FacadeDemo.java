package org.example.designpattern.structural.facade;

import java.math.BigDecimal;

/**
 * 外观模式 —— 演示
 */
public class FacadeDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  对比：不用外观 vs 用外观");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("""
                // ❌ 不用外观：客户端要知道所有子系统，自己协调流程
                InventoryService inventory = new InventoryService();
                PaymentService payment = new PaymentService();
                LogisticsService logistics = new LogisticsService();
                NotificationService notification = new NotificationService();

                if (inventory.checkStock("iPhone", 1)) {
                    inventory.deductStock("iPhone", 1);
                    String payId = payment.createPayment("ORD-001", amount);
                    if (payment.executePayment(payId)) {
                        String trackNo = logistics.createShipment("ORD-001", address);
                        notification.sendSms(phone, "下单成功...");
                    } else {
                        inventory.restoreStock("iPhone", 1);  // 别忘了回滚！
                    }
                }
                // 客户端要了解 4 个子系统 + 流程顺序 + 回滚逻辑 = 太复杂了

                // ✅ 用外观：一行搞定
                OrderFacade facade = new OrderFacade();
                facade.placeOrder("ORD-001", "iPhone", 1, amount, address, phone);
                """);

        OrderFacade facade = new OrderFacade();

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  1. 正常下单 —— 一个方法协调 4 个子系统");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println();

        facade.placeOrder("ORD-001", "iPhone", 1,
                new BigDecimal("6999.00"), "北京市朝阳区xxx", "13800001111");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  2. 再下一单");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println();

        facade.placeOrder("ORD-002", "AirPods", 2,
                new BigDecimal("1798.00"), "上海市浦东新区xxx", "13900002222");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  3. 库存不足 —— 外观自动处理失败情况");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println();

        facade.placeOrder("ORD-003", "MacBook", 100,
                new BigDecimal("1299900.00"), "广州市天河区xxx", "13700003333");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  4. 核心理解");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("""

                外观模式的本质：简化复杂系统的使用

                ┌──────────┐
                │ 客户端    │──── placeOrder() ────► ┌─────────────┐
                │ (简单)    │                        │ OrderFacade │
                └──────────┘                        │ (外观类)     │
                                                    └──────┬──────┘
                                               内部协调多个子系统
                                      ┌────────────┼────────────┐
                                      │            │            │
                                   库存服务     支付服务     物流服务  通知服务

                外观 ≠ 封装所有功能：
                  ✅ 外观提供简化入口，客户端仍然可以直接访问子系统
                  ✅ 外观处理流程编排和异常回滚
                  ✅ 子系统之间通过外观解耦

                Spring 中的外观模式：
                  Service 层就是天然的外观
                  Controller 调一个 Service 方法
                  Service 内部协调多个 Repository、其他 Service、第三方 SDK
                """);
    }
}
