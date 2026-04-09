package org.example.designpattern.behavioral.visitor;

/**
 * 具体元素 —— 图书
 */
public class Book implements Goods {

    private final String name;
    private final double price;
    private final boolean imported;  // 是否进口书

    public Book(String name, double price, boolean imported) {
        this.name = name;
        this.price = price;
        this.imported = imported;
    }

    /**
     * accept —— 双重分派的关键！
     * <p>
     * this 的类型是 Book，所以会调用 visitor.visit(Book)
     * 第一次分派：accept 选择了 Book 这个重载
     * 第二次分派：visitor 的实际类型决定具体行为
     */
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

    public boolean isImported() {
        return imported;
    }
}
