package org.example.designpattern.behavioral.templatemethod;

import java.util.Arrays;
import java.util.List;

/**
 * 具体子类 —— CSV 导出器
 * <p>
 * 只需要实现父类定义的抽象步骤，不需要关心整体流程。
 */
public class CsvDataExporter extends AbstractDataExporter {

    @Override
    protected String getFormatName() {
        return "CSV";
    }

    @Override
    protected void connect() {
        System.out.println("│ [CSV] 连接到数据库...");
    }

    @Override
    protected List<String[]> fetchData(String query) {
        System.out.printf("│ [CSV] 执行查询: %s%n", query);
        // 模拟查询结果
        return List.of(
                new String[]{"张三", "28", "北京"},
                new String[]{"李四", "32", "上海"},
                new String[]{"王五", "25", "广州"}
        );
    }

    @Override
    protected String formatData(List<String[]> data) {
        System.out.println("│ [CSV] 格式化为 CSV（逗号分隔）");
        StringBuilder sb = new StringBuilder();
        sb.append("姓名,年龄,城市\n");  // 表头
        for (String[] row : data) {
            sb.append(String.join(",", row)).append("\n");
        }
        return sb.toString();
    }

    @Override
    protected void writeFile(String content) {
        System.out.println("│ [CSV] 写入文件 export.csv");
        System.out.println("│ 文件内容预览:");
        // 每行加上缩进显示
        Arrays.stream(content.split("\n"))
                .forEach(line -> System.out.println("│   " + line));
    }

    // 不覆盖 cleanup()，使用默认的空实现
}
