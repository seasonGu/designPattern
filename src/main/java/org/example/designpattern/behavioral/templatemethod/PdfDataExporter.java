package org.example.designpattern.behavioral.templatemethod;

import java.util.List;

/**
 * 具体子类 —— PDF 导出器
 * <p>
 * PDF 的格式化更复杂（需要设置页面、字体等），
 * 但整体流程和 CSV、Excel 完全一样 —— 这就是模板方法的威力。
 */
public class PdfDataExporter extends AbstractDataExporter {

    @Override
    protected String getFormatName() {
        return "PDF";
    }

    @Override
    protected void connect() {
        System.out.println("│ [PDF] 连接到数据库...");
    }

    @Override
    protected List<String[]> fetchData(String query) {
        System.out.printf("│ [PDF] 执行查询: %s%n", query);
        return List.of(
                new String[]{"张三", "28", "北京"},
                new String[]{"李四", "32", "上海"},
                new String[]{"王五", "25", "广州"}
        );
    }

    @Override
    protected String formatData(List<String[]> data) {
        System.out.println("│ [PDF] 创建 PDF 文档");
        System.out.println("│ [PDF] 设置页面大小 A4，字体宋体");
        System.out.println("│ [PDF] 绘制表格边框和数据");
        StringBuilder sb = new StringBuilder();
        sb.append("╔══════╦══════╦══════╗\n");
        sb.append("║ 姓名 ║ 年龄 ║ 城市 ║\n");
        sb.append("╠══════╬══════╬══════╣\n");
        for (String[] row : data) {
            sb.append(String.format("║ %-4s ║ %-4s ║ %-4s ║%n", row[0], row[1], row[2]));
        }
        sb.append("╚══════╩══════╩══════╝\n");
        return sb.toString();
    }

    @Override
    protected void writeFile(String content) {
        System.out.println("│ [PDF] 写入文件 export.pdf");
        System.out.println("│ 文件内容预览:");
        content.lines().forEach(line -> System.out.println("│   " + line));
    }

    @Override
    protected void cleanup() {
        System.out.println("│ [PDF] 关闭 PDF 文档流");
    }
}
