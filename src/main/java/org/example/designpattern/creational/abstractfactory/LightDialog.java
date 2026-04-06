package org.example.designpattern.creational.abstractfactory;

public class LightDialog implements Dialog {
    @Override
    public void show(String title, String message) {
        System.out.println("    ┌──────────────────────────────┐");
        System.out.printf("    │ ☀️ %-26s │%n", title);
        System.out.println("    │ 背景:#FFFFFF 阴影:浅灰       │");
        System.out.printf("    │ %-28s │%n", message);
        System.out.println("    │          [确定] [取消]        │");
        System.out.println("    └──────────────────────────────┘");
    }
}
