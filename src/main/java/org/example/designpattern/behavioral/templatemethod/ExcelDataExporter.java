package org.example.designpattern.behavioral.templatemethod;

import java.util.List;

/**
 * 具体子类 —— Excel 导出器
 * <p>
 * Excel 需要额外的资源管理（Workbook），所以覆盖了钩子方法 cleanup()。
 */
public class ExcelDataExporter extends AbstractDataExporter {

    @Override
    protected String getFormatName() {
        return "Excel";
    }

    @Override
    protected void connect() {
        System.out.println("│ [Excel] 连接到数据库...");
    }

    @Override
    protected List<String[]> fetchData(String query) {
        System.out.printf("│ [Excel] 执行查询: %s%n", query);
        return List.of(
                new String[]{"张三", "28", "北京"},
                new String[]{"李四", "32", "上海"},
                new String[]{"王五", "25", "广州"}
        );
    }

    @Override
    protected String formatData(List<String[]> data) {
        System.out.println("│ [Excel] 创建 Workbook，写入 Sheet");
        System.out.println("│ [Excel] 设置单元格样式（加粗表头、自动列宽）");
        StringBuilder sb = new StringBuilder();
        sb.append("| 姓名 | 年龄 | 城市 |\n");
        sb.append("|------|------|------|\n");
        for (String[] row : data) {
            sb.append(String.format("| %-4s | %-4s | %-4s |%n", row[0], row[1], row[2]));
        }
        return sb.toString();
    }

    @Override
    protected void writeFile(String content) {
        System.out.println("│ [Excel] 写入文件 export.xlsx");
        System.out.println("│ 文件内容预览:");
        content.lines().forEach(line -> System.out.println("│   " + line));
    }

    /**
     * 覆盖钩子方法 —— Excel 需要关闭 Workbook 资源
     */
    @Override
    protected void cleanup() {
        System.out.println("│ [Excel] 关闭 Workbook，释放资源");
    }
}
