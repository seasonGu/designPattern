package org.example.designpattern.creational.abstractfactory;

/**
 * 抽象工厂 —— UI 组件工厂
 * <p>
 * 与工厂方法的关键区别：
 * - 工厂方法：一个工厂方法创建一种产品（如 MessageFactory → Message）
 * - 抽象工厂：一个工厂创建一族产品（如 UIFactory → Button + Input + Dialog）
 * <p>
 * 每个具体工厂负责生产一整套风格一致的组件，
 * 保证你不会把「暗色按钮」和「亮色输入框」混搭在一起。
 */
public interface UIFactory {

    Button createButton();

    Input createInput();

    Dialog createDialog();
}
