package org.example.designpattern.structural.facade;

import java.math.BigDecimal;

/**
 * 外观类 —— 订单外观
 * <p>
 * 将库存、支付、物流、通知四个子系统的复杂交互，
 * 封装成一个简单的 placeOrder() 方法。
 * <p>
 * 客户端只需调用一个方法，不需要知道背后有多少子系统在协作。
 * 同时外观类还处理了各步骤失败时的回滚逻辑。
 */
public class OrderFacade {

    private final InventoryService inventoryService;
    private final PaymentService paymentService;
    private final LogisticsService logisticsService;
    private final NotificationService notificationService;

    public OrderFacade() {
        this.inventoryService = new InventoryService();
        this.paymentService = new PaymentService();
        this.logisticsService = new LogisticsService();
        this.notificationService = new NotificationService();
    }

    /**
     * 一键下单 —— 外观方法
     * <p>
     * 背后协调 4 个子系统，处理异常时自动回滚。
     * 客户端只需调用这一个方法。
     */
    public boolean placeOrder(String orderId, String productName, int quantity,
                              BigDecimal amount, String address, String phone) {

        System.out.printf("┌─── 开始处理订单 [%s] ──────────────────%n", orderId);

        // 步骤1: 检查库存
        System.out.println("│ 步骤1: 检查库存");
        if (!inventoryService.checkStock(productName, quantity)) {
            System.out.println("│ ❌ 库存不足，下单失败");
            System.out.printf("└─── 订单 [%s] 处理结束（失败）───────%n", orderId);
            return false;
        }

        // 步骤2: 扣减库存
        System.out.println("│ 步骤2: 扣减库存");
        inventoryService.deductStock(productName, quantity);

        // 步骤3: 创建支付并扣款
        System.out.println("│ 步骤3: 支付");
        String paymentId = paymentService.createPayment(orderId, amount);
        boolean paySuccess = paymentService.executePayment(paymentId);

        if (!paySuccess) {
            // 支付失败 → 回滚库存
            System.out.println("│ ❌ 支付失败，回滚库存...");
            inventoryService.restoreStock(productName, quantity);
            System.out.printf("└─── 订单 [%s] 处理结束（失败）───────%n", orderId);
            return false;
        }

        // 步骤4: 创建物流运单
        System.out.println("│ 步骤4: 创建物流");
        String trackingNo = logisticsService.createShipment(orderId, address);

        // 步骤5: 发送通知
        System.out.println("│ 步骤5: 发送通知");
        notificationService.sendSms(phone,
                String.format("您的订单%s已支付成功，运单号: %s", orderId, trackingNo));

        System.out.printf("│ ✅ 订单 [%s] 下单成功！%n", orderId);
        System.out.printf("└─── 订单 [%s] 处理结束（成功）───────%n", orderId);
        return true;
    }
}
