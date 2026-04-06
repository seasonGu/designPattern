package org.example.designpattern.creational.abstractfactory;

/**
 * 抽象工厂模式 —— 演示
 */
public class AbstractFactoryDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 暗色主题 —— DarkUIFactory 生产一整套暗色组件");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        UIFactory darkFactory = new DarkUIFactory();
        Application darkApp = new Application(darkFactory);
        darkApp.renderLoginPage();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 亮色主题 —— LightUIFactory 生产一整套亮色组件");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        UIFactory lightFactory = new LightUIFactory();
        Application lightApp = new Application(lightFactory);
        lightApp.renderLoginPage();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 运行时切换主题 —— 只需更换工厂");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 模拟从配置读取主题
        String theme = "dark";
        UIFactory factory = createFactory(theme);
        System.out.printf("  当前主题: %s%n", theme);
        new Application(factory).renderLoginPage();

        System.out.println();
        theme = "light";
        factory = createFactory(theme);
        System.out.printf("  当前主题: %s%n", theme);
        new Application(factory).renderLoginPage();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 抽象工厂 vs 工厂方法");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                ┌──────────────┬────────────────────────┬────────────────────────────┐
                │              │ 工厂方法                │ 抽象工厂                    │
                ├──────────────┼────────────────────────┼────────────────────────────┤
                │ 产品数量      │ 一个工厂 → 一种产品     │ 一个工厂 → 一族产品（多个）  │
                │ 解决的问题    │ 创建哪个具体类           │ 创建哪一族产品              │
                │ 扩展方式      │ 新增产品 → 新增工厂类    │ 新增产品族 → 新增工厂类     │
                │ 本项目示例    │ MessageFactory→Message  │ UIFactory→Button+Input+Dialog│
                └──────────────┴────────────────────────┴────────────────────────────┘

                抽象工厂保证了「产品族的一致性」：
                  ✅ DarkFactory  → DarkButton  + DarkInput  + DarkDialog   (风格统一)
                  ✅ LightFactory → LightButton + LightInput + LightDialog  (风格统一)
                  ❌ 不可能出现     DarkButton  + LightInput                (风格混乱)
                """);
    }

    /**
     * 根据配置创建对应的工厂
     */
    private static UIFactory createFactory(String theme) {
        return switch (theme) {
            case "dark" -> new DarkUIFactory();
            case "light" -> new LightUIFactory();
            default -> throw new IllegalArgumentException("未知主题: " + theme);
        };
    }
}
