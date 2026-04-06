package org.example.designpattern.creational.abstractfactory;

public class DarkDialog implements Dialog {
    @Override
    public void show(String title, String message) {
        System.out.println("    ┌──────────────────────────────┐");
        System.out.printf("    │ 🌙 %-26s │%n", title);
        System.out.println("    │ 背景:#2D2D2D 阴影:深黑       │");
        System.out.printf("    │ %-28s │%n", message);
        System.out.println("    │          [确定] [取消]        │");
        System.out.println("    └──────────────────────────────┘");
    }
}
