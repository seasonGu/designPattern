package org.example.designpattern.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题（Subject / 被观察者）—— 订单服务
 * <p>
 * 维护一个观察者列表，当订单状态变化时通知所有观察者。
 * 订单服务不需要知道具体有哪些观察者，也不需要知道它们做什么。
 */
public class OrderService {

    /** 观察者列表 */
    private final List<OrderEventListener> listeners = new ArrayList<>();

    /** 模拟订单当前状态 */
    private OrderStatus currentStatus = OrderStatus.CREATED;

    private final String orderId;

    public OrderService(String orderId) {
        this.orderId = orderId;
    }

    // ═══════════ 管理观察者 ═══════════

    /** 注册观察者 */
    public void addListener(OrderEventListener listener) {
        listeners.add(listener);
    }

    /** 移除观察者 */
    public void removeListener(OrderEventListener listener) {
        listeners.remove(listener);
    }

    // ═══════════ 通知观察者 ═══════════

    /**
     * 通知所有观察者
     * <p>
     * 这是观察者模式的核心：遍历所有观察者，逐一通知。
     */
    private void notifyListeners(OrderEvent event) {
        for (OrderEventListener listener : listeners) {
            listener.onOrderEvent(event);
        }
    }

    // ═══════════ 业务方法（触发事件的源头）═══════════

    /** 支付订单 */
    public void pay() {
        OrderStatus oldStatus = this.currentStatus;
        this.currentStatus = OrderStatus.PAID;
        notifyListeners(new OrderEvent(orderId, oldStatus, currentStatus, "用户完成支付"));
    }

    /** 发货 */
    public void ship() {
        OrderStatus oldStatus = this.currentStatus;
        this.currentStatus = OrderStatus.SHIPPED;
        notifyListeners(new OrderEvent(orderId, oldStatus, currentStatus, "商家已发货，运单号SF123456"));
    }

    /** 确认收货 */
    public void complete() {
        OrderStatus oldStatus = this.currentStatus;
        this.currentStatus = OrderStatus.COMPLETED;
        notifyListeners(new OrderEvent(orderId, oldStatus, currentStatus, "用户确认收货"));
    }

    /** 取消订单 */
    public void cancel() {
        OrderStatus oldStatus = this.currentStatus;
        this.currentStatus = OrderStatus.CANCELLED;
        notifyListeners(new OrderEvent(orderId, oldStatus, currentStatus, "用户主动取消"));
    }
}
