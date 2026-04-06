package org.example.designpattern.creational.abstractfactory;

public class DarkButton implements Button {
    @Override
    public void render() {
        System.out.println("    [🌙 暗色按钮] 背景:#333 文字:#FFF 圆角:8px");
    }

    @Override
    public void onClick() {
        System.out.println("    [🌙 暗色按钮] 点击效果: 灰色波纹扩散");
    }
}
