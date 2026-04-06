package org.example.designpattern.creational.abstractfactory;

public class LightInput implements Input {
    @Override
    public void render() {
        System.out.println("    [☀️ 亮色输入框] 背景:#FFF 边框:#CCC 文字:#333");
    }

    @Override
    public void setValue(String value) {
        System.out.printf("    [☀️ 亮色输入框] 输入: %s (光标颜色: 蓝色)%n", value);
    }
}
