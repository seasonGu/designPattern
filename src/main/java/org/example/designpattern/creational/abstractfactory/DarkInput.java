package org.example.designpattern.creational.abstractfactory;

public class DarkInput implements Input {
    @Override
    public void render() {
        System.out.println("    [🌙 暗色输入框] 背景:#222 边框:#555 文字:#EEE");
    }

    @Override
    public void setValue(String value) {
        System.out.printf("    [🌙 暗色输入框] 输入: %s (光标颜色: 白色)%n", value);
    }
}
