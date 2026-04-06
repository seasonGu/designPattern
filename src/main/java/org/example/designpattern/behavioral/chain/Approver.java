package org.example.designpattern.behavioral.chain;

/**
 * 抽象处理者 —— 审批人
 * <p>
 * 责任链的核心：
 * 1. 持有下一个处理者的引用（next）
 * 2. handle() 方法决定自己处理还是传给下一个
 * 3. 提供链式 setNext() 方法便于构建链
 */
public abstract class Approver {

    protected String name;
    protected String title;
    private Approver next;

    public Approver(String name, String title) {
        this.name = name;
        this.title = title;
    }

    /**
     * 设置下一个处理者（返回下一个，支持链式构建）
     */
    public Approver setNext(Approver next) {
        this.next = next;
        return next;  // 返回 next 而不是 this，便于链式调用
    }

    /**
     * 处理请求（模板方法 + 责任链的结合）
     */
    public final void handle(LeaveRequest request) {
        if (canApprove(request)) {
            approve(request);
        } else if (next != null) {
            System.out.printf("  [%s-%s] %d天超出我的权限，转交给上级%n",
                    title, name, request.days());
            next.handle(request);
        } else {
            // 链的末端，没人能处理
            System.out.printf("  [%s-%s] %d天超出所有人的审批权限，请假被拒绝 ❌%n",
                    title, name, request.days());
        }
    }

    /** 判断自己是否有权限审批 */
    protected abstract boolean canApprove(LeaveRequest request);

    /** 执行审批 */
    protected abstract void approve(LeaveRequest request);
}
