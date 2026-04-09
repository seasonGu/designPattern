package org.example.designpattern.behavioral.visitor;

/**
 * 具体访问者 —— 包装方案
 * <p>
 * 不同商品需要不同的包装方式：
 *   图书 → 纸盒 + 气泡膜
 *   水果 → 保鲜泡沫箱 + 冰袋
 *   电子产品 → 防震气泡 + 木箱
 */
public class PackagingVisitor implements GoodsVisitor {

    @Override
    public void visit(Book book) {
        String packaging = book.isImported() ? "纸盒 + 气泡膜 + 防潮袋(进口)" : "纸盒 + 气泡膜";
        System.out.printf("    📖 %s → 包装方案: %s%n", book.getName(), packaging);
    }

    @Override
    public void visit(Fruit fruit) {
        String packaging = fruit.getWeight() > 5
                ? "加厚泡沫箱 + 大号冰袋 + 加固胶带"
                : "标准泡沫箱 + 冰袋";
        System.out.printf("    🍎 %s (%.1fkg) → 包装方案: %s%n",
                fruit.getName(), fruit.getWeight(), packaging);
    }

    @Override
    public void visit(Electronics electronics) {
        System.out.printf("    📱 %s → 包装方案: 防震气泡膜 + 木箱 + 保修卡(%d年)%n",
                electronics.getName(), electronics.getWarrantyYears());
    }
}
