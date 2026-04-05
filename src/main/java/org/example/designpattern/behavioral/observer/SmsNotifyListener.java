package org.example.designpattern.behavioral.observer;

/**
 * 具体观察者 —— 短信通知服务
 */
public class SmsNotifyListener implements OrderEventListener {

    @Override
    public void onOrderEvent(OrderEvent event) {
        System.out.printf("  📱 [短信服务] 发送短信: 您的订单 %s 状态已更新为【%s】%n",
                event.orderId(), event.newStatus());
    }
}
