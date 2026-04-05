package org.example.designpattern.behavioral.observer;

/**
 * 订单状态枚举
 */
public enum OrderStatus {

    CREATED("已创建"),
    PAID("已支付"),
    SHIPPED("已发货"),
    COMPLETED("已完成"),
    CANCELLED("已取消");

    private final String desc;

    OrderStatus(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return desc;
    }
}
