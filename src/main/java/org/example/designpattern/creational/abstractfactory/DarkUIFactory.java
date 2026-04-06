package org.example.designpattern.creational.abstractfactory;

/**
 * 具体工厂 —— 暗色主题工厂
 * <p>
 * 生产一整套暗色风格的组件：暗色按钮 + 暗色输入框 + 暗色对话框。
 * 保证同一主题下所有组件风格一致。
 */
public class DarkUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new DarkButton();
    }

    @Override
    public Input createInput() {
        return new DarkInput();
    }

    @Override
    public Dialog createDialog() {
        return new DarkDialog();
    }
}
