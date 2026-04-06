package org.example.designpattern.behavioral.templatemethod;

/**
 * 模板方法模式 —— 演示
 */
public class TemplateMethodDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 三种导出器，相同流程，不同实现");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 三种导出器，调用方式完全一样！
        AbstractDataExporter csvExporter = new CsvDataExporter();
        csvExporter.export("SELECT * FROM users");

        System.out.println();

        AbstractDataExporter excelExporter = new ExcelDataExporter();
        excelExporter.export("SELECT * FROM users");

        System.out.println();

        AbstractDataExporter pdfExporter = new PdfDataExporter();
        pdfExporter.export("SELECT * FROM users");

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 多态的力量 —— 统一接口处理不同格式");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 实际项目中，格式可由用户选择或配置决定
        AbstractDataExporter[] exporters = {
                new CsvDataExporter(),
                new ExcelDataExporter(),
                new PdfDataExporter()
        };

        for (AbstractDataExporter exporter : exporters) {
            // 调用方完全不关心具体格式，统一调用 export()
            exporter.export("SELECT name, age FROM employees LIMIT 3");
            System.out.println();
        }

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""
                模板方法的三种方法类型：

                ① 模板方法（final）：export()
                   → 定义算法骨架，不可覆盖
                   → 控制整体流程：连接 → 查询 → 格式化 → 写文件 → 清理

                ② 抽象方法（abstract）：connect(), fetchData(), formatData(), writeFile()
                   → 子类必须实现
                   → 每个子类提供自己的具体逻辑

                ③ 钩子方法（hook）：cleanup()
                   → 提供默认实现（空方法），子类可选覆盖
                   → CsvExporter 不需要清理 → 不覆盖
                   → ExcelExporter 需要关闭 Workbook → 覆盖
                   → PdfExporter 需要关闭文档流 → 覆盖

                设计原则：「好莱坞原则 —— 别调用我们，我们会调用你」
                父类控制流程，在合适的时机调用子类的方法。
                """);
    }
}
