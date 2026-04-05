package org.example.designpattern.behavioral.observer;

/**
 * 具体观察者 —— 邮件通知服务
 */
public class EmailNotifyListener implements OrderEventListener {

    @Override
    public void onOrderEvent(OrderEvent event) {
        System.out.printf("  📧 [邮件服务] 发送邮件: 订单 %s 从【%s】变为【%s】，备注: %s%n",
                event.orderId(), event.oldStatus(), event.newStatus(), event.remark());
    }
}
