package org.example.designpattern.behavioral.chain;

/**
 * 责任链模式 —— 演示
 */
public class ChainDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  构建审批链: 组长 → 经理 → 总监 → CEO");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 构建责任链
        Approver teamLeader = new TeamLeader("赵六");
        Approver manager = new Manager("王五");
        Approver director = new Director("李四");
        Approver ceo = new CEO("张三");

        // 链式连接: 组长 → 经理 → 总监 → CEO
        teamLeader.setNext(manager).setNext(director).setNext(ceo);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 请假1天 → 组长直接审批");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();
        teamLeader.handle(new LeaveRequest("小明", 1, "感冒看病"));

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 请假3天 → 组长转交 → 经理审批");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();
        teamLeader.handle(new LeaveRequest("小红", 3, "家里有事"));

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 请假5天 → 组长转交 → 经理转交 → 总监审批");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();
        teamLeader.handle(new LeaveRequest("小刚", 5, "出去旅游"));

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 请假10天 → 一路转交到 CEO 审批");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();
        teamLeader.handle(new LeaveRequest("小李", 10, "回老家结婚"));

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 请假30天 → 全链无人能批，拒绝");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();
        teamLeader.handle(new LeaveRequest("小王", 30, "环游世界"));

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  6. 动态调整链 —— 跳过经理直接到总监");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        Approver shortChain = new TeamLeader("赵六");
        shortChain.setNext(new Director("李四")).setNext(new CEO("张三"));

        System.out.println("  短链: 组长 → 总监 → CEO（跳过经理）");
        shortChain.handle(new LeaveRequest("小陈", 2, "搬家"));

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  7. 对比：不用责任链 vs 用责任链");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                // ❌ 不用责任链：if-else 地狱，耦合严重
                void approve(LeaveRequest req) {
                    if (req.days() <= 1)       teamLeader.approve(req);
                    else if (req.days() <= 3)  manager.approve(req);
                    else if (req.days() <= 7)  director.approve(req);
                    else if (req.days() <= 15) ceo.approve(req);
                    // 新增一级审批人？改这里... ❌
                }

                // ✅ 用责任链：发送者只需把请求交给链头，不关心谁来处理
                teamLeader.handle(request);   // 一行搞定
                // 新增审批人？插入链中即可，不改已有代码 ✅

                // Spring 中的责任链：
                // FilterChain.doFilter(request, response)
                // 每个 Filter 决定: 处理 or chain.doFilter() 传给下一个
                """);
    }
}
