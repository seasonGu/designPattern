package org.example.designpattern.creational.abstractfactory;

/**
 * 客户端 —— 应用程序
 * <p>
 * 只依赖抽象工厂接口和产品接口，完全不知道具体是什么主题。
 * 传入不同的工厂，整套 UI 就自动切换。
 */
public class Application {

    private final Button button;
    private final Input input;
    private final Dialog dialog;

    /**
     * 构造时传入工厂，由工厂创建一整套组件
     */
    public Application(UIFactory factory) {
        this.button = factory.createButton();
        this.input = factory.createInput();
        this.dialog = factory.createDialog();
    }

    /**
     * 渲染页面 —— 使用的组件风格完全取决于传入的工厂
     */
    public void renderLoginPage() {
        System.out.println("  ── 渲染登录页面 ──");
        input.render();
        input.setValue("zhangsan");
        button.render();
        button.onClick();
        dialog.show("登录结果", "登录成功！欢迎回来");
    }
}
