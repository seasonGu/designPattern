package org.example.designpattern.creational.prototype;

/**
 * 原型接口 —— 游戏单位
 * <p>
 * 定义克隆方法。所有可克隆的游戏对象都实现这个接口。
 * Java 的 Cloneable 接口也是原型模式，但设计上有缺陷，
 * 这里我们自定义一个更清晰的接口。
 */
public interface GameUnit {

    /**
     * 克隆自身（深拷贝）
     */
    GameUnit clone();

    /**
     * 显示单位信息
     */
    void showInfo();
}
