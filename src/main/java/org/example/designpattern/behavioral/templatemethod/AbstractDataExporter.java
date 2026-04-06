package org.example.designpattern.behavioral.templatemethod;

import java.util.List;

/**
 * 抽象类 —— 数据导出器
 * <p>
 * 这是模板方法模式的核心：
 * - {@link #export(String)} 是模板方法，用 final 修饰，子类不可覆盖
 * - 它定义了导出的固定流程：连接 → 查询 → 格式化 → 写文件 → 清理
 * - 具体步骤由子类实现
 */
public abstract class AbstractDataExporter {

    /**
     * 模板方法 —— 定义算法骨架（final，不可覆盖！）
     * <p>
     * 这就是"模板"：流程固定，步骤可变。
     * 无论是 CSV、Excel 还是 PDF，都走这个流程。
     */
    public final void export(String query) {
        System.out.printf("┌─── 开始导出【%s】格式 ───%n", getFormatName());

        // 步骤1：连接数据源
        connect();

        // 步骤2：查询数据
        List<String[]> data = fetchData(query);
        System.out.printf("│ 查询到 %d 条数据%n", data.size());

        // 步骤3：格式化数据
        String formatted = formatData(data);

        // 步骤4：写入文件
        writeFile(formatted);

        // 步骤5：清理资源（钩子方法，子类可选覆盖）
        cleanup();

        System.out.printf("└─── 导出【%s】完成 ✅ ───%n", getFormatName());
    }

    // ═══════════ 抽象步骤（子类必须实现）═══════════

    /** 获取格式名称 */
    protected abstract String getFormatName();

    /** 连接数据源 */
    protected abstract void connect();

    /** 查询数据 */
    protected abstract List<String[]> fetchData(String query);

    /** 格式化数据为目标格式 */
    protected abstract String formatData(List<String[]> data);

    /** 写入文件 */
    protected abstract void writeFile(String content);

    // ═══════════ 钩子方法（子类可选覆盖）═══════════

    /**
     * 清理资源 —— 钩子方法
     * <p>
     * 提供默认实现（什么都不做），子类可以根据需要覆盖。
     * 比如需要关闭数据库连接、释放文件句柄等。
     */
    protected void cleanup() {
        // 默认不做任何清理，子类按需覆盖
    }
}
