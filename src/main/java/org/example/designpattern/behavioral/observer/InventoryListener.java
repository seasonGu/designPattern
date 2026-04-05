package org.example.designpattern.behavioral.observer;

/**
 * 具体观察者 —— 库存服务
 * <p>
 * 只关心特定事件：订单取消时需要恢复库存。
 * 观察者可以选择性处理自己感兴趣的事件。
 */
public class InventoryListener implements OrderEventListener {

    @Override
    public void onOrderEvent(OrderEvent event) {
        if (event.newStatus() == OrderStatus.CANCELLED) {
            System.out.printf("  📦 [库存服务] 订单 %s 已取消，恢复库存%n", event.orderId());
        } else if (event.newStatus() == OrderStatus.PAID) {
            System.out.printf("  📦 [库存服务] 订单 %s 已支付，锁定库存%n", event.orderId());
        }
        // 其他状态不关心，直接忽略
    }
}
