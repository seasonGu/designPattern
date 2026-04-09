package org.example.designpattern.behavioral.state;

/**
 * 具体状态 —— 新建状态
 * <p>
 * 允许的操作：pay()（支付）、cancel()（取消）
 * 不允许：ship()、receive()
 */
public class NewState implements OrderState {

    @Override
    public String getName() {
        return "新建";
    }

    @Override
    public void pay(Order order) {
        System.out.printf("    [操作] 支付 %.2f 元...%n", order.getAmount());
        System.out.println("    [结果] 支付成功 ✅");
        // 状态流转：新建 → 已支付
        order.setState(new PaidState());
    }

    @Override
    public void ship(Order order) {
        // 新建状态不能发货
        System.out.println("    [操作] 尝试发货...");
        System.out.println("    [结果] 失败 ❌ 订单未支付，不能发货");
    }

    @Override
    public void receive(Order order) {
        // 新建状态不能确认收货
        System.out.println("    [操作] 尝试确认收货...");
        System.out.println("    [结果] 失败 ❌ 订单未发货，不能确认收货");
    }

    @Override
    public void cancel(Order order) {
        System.out.println("    [操作] 取消订单...");
        System.out.println("    [结果] 订单已取消 ✅（无需退款）");
        // 状态流转：新建 → 已取消
        order.setState(new CancelledState());
    }
}
