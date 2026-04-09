package org.example.designpattern.behavioral.state;

/**
 * 具体状态 —— 已发货状态
 * <p>
 * 允许的操作：receive()（确认收货）
 * 不允许：pay()、ship()、cancel()（已发货不能取消）
 */
public class ShippedState implements OrderState {

    @Override
    public String getName() {
        return "已发货";
    }

    @Override
    public void pay(Order order) {
        System.out.println("    [操作] 尝试支付...");
        System.out.println("    [结果] 失败 ❌ 订单已支付并发货");
    }

    @Override
    public void ship(Order order) {
        System.out.println("    [操作] 尝试发货...");
        System.out.println("    [结果] 失败 ❌ 订单已发货，请勿重复操作");
    }

    @Override
    public void receive(Order order) {
        System.out.println("    [操作] 买家确认收货...");
        System.out.println("    [结果] 收货成功，订单完成 ✅");
        // 状态流转：已发货 → 已完成
        order.setState(new CompletedState());
    }

    @Override
    public void cancel(Order order) {
        // 已发货不能取消 —— 这是状态模式的价值所在！
        System.out.println("    [操作] 尝试取消订单...");
        System.out.println("    [结果] 失败 ❌ 商品已发出，无法取消，请拒收后申请退款");
    }
}
