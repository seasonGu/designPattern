package org.example.designpattern.behavioral.observer;

/**
 * 观察者接口 —— 订单事件监听器
 * <p>
 * 所有想要监听订单状态变化的模块都实现这个接口。
 */
public interface OrderEventListener {

    /**
     * 当订单状态变化时被调用
     *
     * @param event 订单事件
     */
    void onOrderEvent(OrderEvent event);
}
