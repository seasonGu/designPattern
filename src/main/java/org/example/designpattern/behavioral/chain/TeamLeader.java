package org.example.designpattern.behavioral.chain;

/**
 * 具体处理者 —— 组长（可批 ≤1天）
 */
public class TeamLeader extends Approver {

    public TeamLeader(String name) {
        super(name, "组长");
    }

    @Override
    protected boolean canApprove(LeaveRequest request) {
        return request.days() <= 1;
    }

    @Override
    protected void approve(LeaveRequest request) {
        System.out.printf("  [组长-%s] 审批通过 ✅ %s 请假%d天（原因: %s）%n",
                name, request.employeeName(), request.days(), request.reason());
    }
}
