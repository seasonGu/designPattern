package org.example.designpattern.behavioral.observer;

/**
 * 事件对象 —— 订单事件
 * <p>
 * 封装了事件发生时的所有信息，传递给观察者。
 * 用 record（Java 16+）来简洁定义不可变数据类。
 */
public record OrderEvent(
        String orderId,
        OrderStatus oldStatus,
        OrderStatus newStatus,
        String remark
) {
    @Override
    public String toString() {
        return String.format("OrderEvent{订单=%s, %s → %s, 备注='%s'}",
                orderId, oldStatus, newStatus, remark);
    }
}
