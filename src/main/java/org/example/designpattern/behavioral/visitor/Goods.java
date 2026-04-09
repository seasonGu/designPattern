package org.example.designpattern.behavioral.visitor;

/**
 * 元素接口 —— 商品
 * <p>
 * 所有商品都必须实现 accept() 方法，接受访问者的访问。
 * 这是双重分派的第一步。
 */
public interface Goods {

    /** 接受访问者 —— 双重分派的关键 */
    void accept(GoodsVisitor visitor);

    /** 商品名称 */
    String getName();

    /** 商品价格 */
    double getPrice();
}
