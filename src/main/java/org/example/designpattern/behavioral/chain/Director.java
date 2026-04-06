package org.example.designpattern.behavioral.chain;

/**
 * 具体处理者 —— 总监（可批 ≤7天）
 */
public class Director extends Approver {

    public Director(String name) {
        super(name, "总监");
    }

    @Override
    protected boolean canApprove(LeaveRequest request) {
        return request.days() <= 7;
    }

    @Override
    protected void approve(LeaveRequest request) {
        System.out.printf("  [总监-%s] 审批通过 ✅ %s 请假%d天（原因: %s）%n",
                name, request.employeeName(), request.days(), request.reason());
    }
}
