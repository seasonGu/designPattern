package org.example.designpattern.behavioral.visitor;

/**
 * 具体访问者 —— 出口报表
 * <p>
 * 新增的操作！不需要修改任何商品类，只需实现 GoodsVisitor 即可。
 * 这就是访问者模式的威力 —— 开闭原则。
 */
public class ExportReportVisitor implements GoodsVisitor {

    private final StringBuilder report = new StringBuilder();
    private int itemCount = 0;

    @Override
    public void visit(Book book) {
        itemCount++;
        String importFlag = book.isImported() ? "进口" : "国产";
        report.append(String.format("    | %-4d | 图书     | %-12s | %8.2f | %s |%n",
                itemCount, book.getName(), book.getPrice(), importFlag));
    }

    @Override
    public void visit(Fruit fruit) {
        itemCount++;
        report.append(String.format("    | %-4d | 水果     | %-12s | %8.2f | %.1fkg |%n",
                itemCount, fruit.getName(), fruit.getPrice(), fruit.getWeight()));
    }

    @Override
    public void visit(Electronics electronics) {
        itemCount++;
        report.append(String.format("    | %-4d | 电子产品 | %-12s | %8.2f | 保修%d年 |%n",
                itemCount, electronics.getName(), electronics.getPrice(),
                electronics.getWarrantyYears()));
    }

    public void printReport() {
        System.out.println("    ┌──────┬──────────┬──────────────┬──────────┬────────┐");
        System.out.println("    | 序号 | 类型     | 名称         |     价格 | 备注   |");
        System.out.println("    ├──────┼──────────┼──────────────┼──────────┼────────┤");
        System.out.print(report);
        System.out.println("    └──────┴──────────┴──────────────┴──────────┴────────┘");
        System.out.printf("    共 %d 件商品%n", itemCount);
    }
}
