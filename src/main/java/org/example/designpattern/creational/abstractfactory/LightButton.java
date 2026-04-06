package org.example.designpattern.creational.abstractfactory;

public class LightButton implements Button {
    @Override
    public void render() {
        System.out.println("    [☀️ 亮色按钮] 背景:#4A90D9 文字:#FFF 圆角:8px");
    }

    @Override
    public void onClick() {
        System.out.println("    [☀️ 亮色按钮] 点击效果: 蓝色波纹扩散");
    }
}
