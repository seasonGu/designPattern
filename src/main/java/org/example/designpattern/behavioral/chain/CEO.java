package org.example.designpattern.behavioral.chain;

/**
 * 具体处理者 —— CEO（可批 ≤15天，超过拒绝）
 */
public class CEO extends Approver {

    public CEO(String name) {
        super(name, "CEO");
    }

    @Override
    protected boolean canApprove(LeaveRequest request) {
        return request.days() <= 15;
    }

    @Override
    protected void approve(LeaveRequest request) {
        System.out.printf("  [CEO-%s] 审批通过 ✅ %s 请假%d天（原因: %s）%n",
                name, request.employeeName(), request.days(), request.reason());
    }
}
