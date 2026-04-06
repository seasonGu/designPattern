package org.example.designpattern.creational.abstractfactory;

/**
 * 具体工厂 —— 亮色主题工厂
 * <p>
 * 生产一整套亮色风格的组件：亮色按钮 + 亮色输入框 + 亮色对话框。
 */
public class LightUIFactory implements UIFactory {

    @Override
    public Button createButton() {
        return new LightButton();
    }

    @Override
    public Input createInput() {
        return new LightInput();
    }

    @Override
    public Dialog createDialog() {
        return new LightDialog();
    }
}
