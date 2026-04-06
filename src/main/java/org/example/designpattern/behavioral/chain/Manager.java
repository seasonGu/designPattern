package org.example.designpattern.behavioral.chain;

/**
 * 具体处理者 —— 经理（可批 ≤3天）
 */
public class Manager extends Approver {

    public Manager(String name) {
        super(name, "经理");
    }

    @Override
    protected boolean canApprove(LeaveRequest request) {
        return request.days() <= 3;
    }

    @Override
    protected void approve(LeaveRequest request) {
        System.out.printf("  [经理-%s] 审批通过 ✅ %s 请假%d天（原因: %s）%n",
                name, request.employeeName(), request.days(), request.reason());
    }
}
