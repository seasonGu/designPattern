package org.example.designpattern.behavioral.state;

/**
 * 具体状态 —— 已支付状态
 * <p>
 * 允许的操作：ship()（发货）、cancel()（取消并退款）
 * 不允许：pay()（重复支付）、receive()（还没发货）
 */
public class PaidState implements OrderState {

    @Override
    public String getName() {
        return "已支付";
    }

    @Override
    public void pay(Order order) {
        // 已经支付过了
        System.out.println("    [操作] 尝试支付...");
        System.out.println("    [结果] 失败 ❌ 订单已支付，请勿重复付款");
    }

    @Override
    public void ship(Order order) {
        System.out.println("    [操作] 商家发货...");
        System.out.println("    [结果] 已发货 ✅ 快递单号: SF20260407001");
        // 状态流转：已支付 → 已发货
        order.setState(new ShippedState());
    }

    @Override
    public void receive(Order order) {
        // 还没发货，不能确认收货
        System.out.println("    [操作] 尝试确认收货...");
        System.out.println("    [结果] 失败 ❌ 订单未发货，不能确认收货");
    }

    @Override
    public void cancel(Order order) {
        System.out.printf("    [操作] 取消订单，退款 %.2f 元...%n", order.getAmount());
        System.out.println("    [结果] 订单已取消，退款处理中 ✅");
        // 状态流转：已支付 → 已取消
        order.setState(new CancelledState());
    }
}
