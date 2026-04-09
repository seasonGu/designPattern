package org.example.designpattern.behavioral.visitor;

/**
 * 具体元素 —— 水果
 */
public class Fruit implements Goods {

    private final String name;
    private final double price;
    private final double weight;  // 重量(kg)

    public Fruit(String name, double price, double weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
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

    public double getWeight() {
        return weight;
    }
}
