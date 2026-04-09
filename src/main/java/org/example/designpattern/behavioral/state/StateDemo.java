package org.example.designpattern.behavioral.state;

/**
 * 状态模式 —— 演示
 */
public class StateDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  问题：不用状态模式，代码会怎样？");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                不用状态模式的 pay() 方法：
                void pay() {
                    if (state.equals("新建")) {
                        // 处理支付...
                        state = "已支付";
                    } else if (state.equals("已支付")) {
                        System.out.println("已支付，请勿重复付款");
                    } else if (state.equals("已发货")) {
                        System.out.println("已发货，无需支付");
                    } else if (state.equals("已完成")) {
                        System.out.println("订单已完成");
                    } else if (state.equals("已取消")) {
                        System.out.println("订单已取消");
                    }
                }
                // ship()、receive()、cancel() 每个方法都要写类似的 if-else
                // 5种状态 × 4个操作 = 20个分支，散落在各个方法中！
                // 新增一种状态？每个方法都要改！违反开闭原则！
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 正常流程：新建 → 已支付 → 已发货 → 已完成");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        Order order1 = new Order("ORD-001", 299.00);
        System.out.println();

        System.out.println("  ▶ 支付订单:");
        order1.pay();
        System.out.println();

        System.out.println("  ▶ 商家发货:");
        order1.ship();
        System.out.println();

        System.out.println("  ▶ 确认收货:");
        order1.receive();
        System.out.println();

        System.out.printf("  最终状态: %s%n", order1.getStateName());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 非法操作 —— 状态自动拦截");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        Order order2 = new Order("ORD-002", 599.00);
        System.out.println();

        System.out.println("  ▶ 未支付就发货（应该失败）:");
        order2.ship();
        System.out.println();

        System.out.println("  ▶ 未发货就确认收货（应该失败）:");
        order2.receive();
        System.out.println();

        System.out.println("  ▶ 正常支付:");
        order2.pay();
        System.out.println();

        System.out.println("  ▶ 重复支付（应该失败）:");
        order2.pay();
        System.out.println();

        System.out.println("  ▶ 发货:");
        order2.ship();
        System.out.println();

        System.out.println("  ▶ 已发货后取消（应该失败）:");
        order2.cancel();
        System.out.println();

        System.out.printf("  当前状态: %s%n", order2.getStateName());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 取消订单 —— 不同状态下取消行为不同");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 3a. 新建状态取消（无需退款）
        System.out.println("  --- 场景 A: 新建状态取消 ---");
        Order order3a = new Order("ORD-003A", 128.00);
        System.out.println();
        System.out.println("  ▶ 取消订单:");
        order3a.cancel();
        System.out.printf("  最终状态: %s%n", order3a.getStateName());
        System.out.println();

        // 3b. 已支付状态取消（需要退款）
        System.out.println("  --- 场景 B: 已支付状态取消 ---");
        Order order3b = new Order("ORD-003B", 256.00);
        order3b.pay();
        System.out.println();
        System.out.println("  ▶ 取消订单（会触发退款）:");
        order3b.cancel();
        System.out.printf("  最终状态: %s%n", order3b.getStateName());
        System.out.println();

        // 3c. 已取消后再操作（全部失败）
        System.out.println("  --- 场景 C: 已取消后尝试操作 ---");
        System.out.println("  ▶ 尝试支付已取消的订单:");
        order3a.pay();
        System.out.println();

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 已完成后尝试操作（全部失败）");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        System.out.printf("  订单 %s 当前状态: %s%n", order1.getOrderId(), order1.getStateName());
        System.out.println();

        System.out.println("  ▶ 尝试再次支付:");
        order1.pay();
        System.out.println();

        System.out.println("  ▶ 尝试取消:");
        order1.cancel();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                状态模式的关键：把 if-else 变成多态

                不用状态模式：                      用状态模式：
                ┌──────────────────────┐        ┌──────────────────────┐
                │ Order                │        │ Order                │
                │ void pay() {        │        │ void pay() {        │
                │   if (新建) ...      │        │   state.pay(this);  │  ← 一行搞定！
                │   if (已支付) ...    │        │ }                   │
                │   if (已发货) ...    │        └──────────┬───────────┘
                │   if (已完成) ...    │                   │ 委托
                │   if (已取消) ...    │           ┌───────┴────────┐
                │ }                   │       NewState        PaidState ...
                │ // ship()同样5个if  │       pay() {         pay() {
                │ // receive()同样    │         支付成功          已支付，拒绝
                │ // cancel()同样     │         → PaidState    }
                └──────────────────────┘       }

                优势：
                  ✅ 每种状态的逻辑封装在独立类中，职责清晰
                  ✅ 新增状态只需新增一个类，不改已有代码（开闭原则）
                  ✅ 状态转换规则一目了然（在各状态类中）
                  ✅ 消除大量 if-else / switch-case

                Spring 中的状态模式：
                  Spring Statemachine —— 专门的状态机框架
                  Spring WebFlow —— 页面流转也是状态模式思想
                """);
    }
}
