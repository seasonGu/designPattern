package org.example.designpattern.behavioral.state;

/**
 * 具体状态 —— 已完成状态（终态）
 * <p>
 * 所有操作都不允许 —— 订单已结束。
 */
public class CompletedState implements OrderState {

    @Override
    public String getName() {
        return "已完成";
    }

    @Override
    public void pay(Order order) {
        System.out.println("    [操作] 尝试支付...");
        System.out.println("    [结果] 失败 ❌ 订单已完成");
    }

    @Override
    public void ship(Order order) {
        System.out.println("    [操作] 尝试发货...");
        System.out.println("    [结果] 失败 ❌ 订单已完成");
    }

    @Override
    public void receive(Order order) {
        System.out.println("    [操作] 尝试确认收货...");
        System.out.println("    [结果] 失败 ❌ 订单已完成，请勿重复操作");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("    [操作] 尝试取消订单...");
        System.out.println("    [结果] 失败 ❌ 订单已完成，如需退货请申请售后");
    }
}
