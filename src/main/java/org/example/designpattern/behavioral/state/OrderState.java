package org.example.designpattern.behavioral.state;

/**
 * 状态接口 —— 订单状态
 * <p>
 * 定义在每种状态下可能的操作。
 * 每个具体状态类决定在该状态下这些操作的具体行为。
 */
public interface OrderState {

    /** 获取状态名称 */
    String getName();

    /** 支付 */
    void pay(Order order);

    /** 发货 */
    void ship(Order order);

    /** 确认收货 */
    void receive(Order order);

    /** 取消订单 */
    void cancel(Order order);
}
