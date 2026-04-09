package org.example.designpattern.behavioral.visitor;

/**
 * 具体元素 —— 电子产品
 */
public class Electronics implements Goods {

    private final String name;
    private final double price;
    private final int warrantyYears;  // 保修年限

    public Electronics(String name, double price, int warrantyYears) {
        this.name = name;
        this.price = price;
        this.warrantyYears = warrantyYears;
    }

    @Override
    public void accept(GoodsVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }
}
