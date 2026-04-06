package org.example.designpattern.behavioral.chain;

/**
 * 请假申请
 */
public record LeaveRequest(
        String employeeName,
        int days,
        String reason
) {
    @Override
    public String toString() {
        return String.format("请假申请{%s, %d天, 原因='%s'}", employeeName, days, reason);
    }
}
