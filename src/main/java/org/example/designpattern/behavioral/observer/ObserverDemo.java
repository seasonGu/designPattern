package org.example.designpattern.behavioral.observer;

/**
 * 观察者模式 —— 演示
 */
public class ObserverDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════");
        System.out.println("  1. 注册观察者 & 订单正常流程");
        System.out.println("═══════════════════════════════════════════════");

        OrderService order = new OrderService("ORD-2024-001");

        // 注册三个观察者
        SmsNotifyListener smsListener = new SmsNotifyListener();
        EmailNotifyListener emailListener = new EmailNotifyListener();
        InventoryListener inventoryListener = new InventoryListener();

        order.addListener(smsListener);
        order.addListener(emailListener);
        order.addListener(inventoryListener);

        System.out.println("【支付订单】");
        order.pay();
        // 三个观察者都会收到通知

        System.out.println();
        System.out.println("【商家发货】");
        order.ship();
        // 库存服务对"发货"不感兴趣，不会有输出

        System.out.println();
        System.out.println("【确认收货】");
        order.complete();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("  2. 动态移除观察者");
        System.out.println("═══════════════════════════════════════════════");

        OrderService order2 = new OrderService("ORD-2024-002");
        order2.addListener(smsListener);
        order2.addListener(emailListener);
        order2.addListener(inventoryListener);

        System.out.println("【支付订单】（3个观察者）");
        order2.pay();

        System.out.println();
        // 用户关闭了短信通知
        System.out.println("--- 用户关闭了短信通知 ---");
        order2.removeListener(smsListener);

        System.out.println("【发货】（只剩2个观察者）");
        order2.ship();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("  3. 取消订单 —— 库存服务选择性响应");
        System.out.println("═══════════════════════════════════════════════");

        OrderService order3 = new OrderService("ORD-2024-003");
        order3.addListener(smsListener);
        order3.addListener(emailListener);
        order3.addListener(inventoryListener);

        System.out.println("【支付】");
        order3.pay();
        System.out.println();
        System.out.println("【取消订单】");
        order3.cancel();
        // 注意看：库存服务只在"已支付"和"已取消"时才有动作

        System.out.println();
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("  4. 对比：不用观察者模式的写法");
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("""
                // ❌ 不用观察者：订单服务直接依赖所有通知模块
                public void pay() {
                    this.status = PAID;
                    smsService.send(...);        // 硬编码依赖
                    emailService.send(...);      // 硬编码依赖
                    inventoryService.lock(...);  // 硬编码依赖
                    // 每新增一个通知渠道，就要改这里 ❌
                }

                // ✅ 用观察者：订单服务只管发事件，不关心谁在听
                public void pay() {
                    this.status = PAID;
                    notifyListeners(event);  // 一行搞定，永远不用改 ✅
                }
                """);
    }
}
