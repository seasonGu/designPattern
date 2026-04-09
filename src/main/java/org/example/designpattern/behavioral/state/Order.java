package org.example.designpattern.behavioral.state;

/**
 * 上下文类 —— 订单
 * <p>
 * 持有当前状态的引用，所有行为委托给当前状态来执行。
 * 状态的切换由具体状态类来完成（调用 order.setState()）。
 */
public class Order {

    /** 订单ID */
    private final String orderId;

    /** 当前状态 —— 核心！所有行为都委托给它 */
    private OrderState state;

    /** 订单金额 */
    private final double amount;

    public Order(String orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
        // 初始状态：新建
        this.state = new NewState();
        System.out.printf("    [订单 %s] 创建成功，金额: %.2f 元，状态: %s%n",
                orderId, amount, state.getName());
    }

    // ─────────────────────────────────────────────
    //  对外操作 —— 全部委托给当前状态
    // ─────────────────────────────────────────────

    /** 支付 —— 委托给当前状态 */
    public void pay() {
        state.pay(this);
    }

    /** 发货 —— 委托给当前状态 */
    public void ship() {
        state.ship(this);
    }

    /** 确认收货 —— 委托给当前状态 */
    public void receive() {
        state.receive(this);
    }

    /** 取消订单 —— 委托给当前状态 */
    public void cancel() {
        state.cancel(this);
    }

    // ─────────────────────────────────────────────
    //  Getter / Setter
    // ─────────────────────────────────────────────

    /** 状态切换 —— 由具体状态类调用 */
    public void setState(OrderState newState) {
        System.out.printf("    [订单 %s] 状态变更: %s → %s%n",
                orderId, this.state.getName(), newState.getName());
        this.state = newState;
    }

    public OrderState getState() {
        return state;
    }

    public String getOrderId() {
        return orderId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStateName() {
        return state.getName();
    }
}
