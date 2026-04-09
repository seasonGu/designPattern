package org.example.designpattern.behavioral.visitor;

import java.util.List;

/**
 * 访问者模式 —— 演示
 */
public class VisitorDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  问题：不用访问者，新增操作要怎样？");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                不用访问者模式，想给商品新增「包装方案」功能：
                  class Book {
                      double discount() { ... }  // 已有
                      String packaging() { ... }  // 新增 ← 改 Book
                  }
                  class Fruit {
                      double discount() { ... }
                      String packaging() { ... }  // 新增 ← 改 Fruit
                  }
                  class Electronics {
                      double discount() { ... }
                      String packaging() { ... }  // 新增 ← 改 Electronics
                  }
                  // 每新增一种操作，3个类全部要改！违反开闭原则！

                用访问者模式：
                  新增操作 = 新增一个 Visitor 实现类，商品类一行不改！
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 创建商品列表");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 商品列表 —— 类型固定（Book, Fruit, Electronics）
        List<Goods> goodsList = List.of(
                new Book("Java编程思想", 108.00, false),
                new Book("Design Patterns", 256.00, true),
                new Fruit("山东苹果", 39.90, 3.0),
                new Fruit("进口车厘子", 199.00, 8.0),
                new Electronics("无线耳机", 599.00, 1),
                new Electronics("笔记本电脑", 6999.00, 3)
        );

        System.out.printf("  购物车共 %d 件商品%n", goodsList.size());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 访问者 #1 —— 折扣计算");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        DiscountVisitor discountVisitor = new DiscountVisitor();
        for (Goods goods : goodsList) {
            goods.accept(discountVisitor);  // 双重分派！
        }
        System.out.println();
        discountVisitor.printSummary();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 访问者 #2 —— 包装方案");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        PackagingVisitor packagingVisitor = new PackagingVisitor();
        for (Goods goods : goodsList) {
            goods.accept(packagingVisitor);
        }

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 访问者 #3 —— 出口报表（新增操作，不改商品类!）");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        ExportReportVisitor reportVisitor = new ExportReportVisitor();
        for (Goods goods : goodsList) {
            goods.accept(reportVisitor);
        }
        System.out.println();
        reportVisitor.printReport();

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                访问者模式的关键：双重分派 (Double Dispatch)

                goods.accept(visitor)  的执行过程：
                ┌────────┐  accept(visitor)  ┌──────────────┐
                │ Book   │ ────────────────► │ visitor.     │
                │        │                   │ visit(this)  │ ← this 是 Book 类型
                └────────┘                   └──────┬───────┘
                                                    │
                                    ┌───────────────┼───────────────┐
                                    │               │               │
                             DiscountVisitor  PackagingVisitor  ReportVisitor
                             visit(Book) {    visit(Book) {    visit(Book) {
                               打9折            纸盒包装          生成报表行
                             }                }                }

                第一次分派：goods 的实际类型 → 决定调用哪个 accept
                第二次分派：visitor 的实际类型 → 决定调用哪个 visit 实现

                何时用 / 不用：
                  ✅ 元素类型稳定（商品种类固定），操作经常变化（新增折扣规则、报表...）
                  ❌ 元素类型经常变化（每加一种，所有 Visitor 都要改）

                Java/Spring 中的访问者模式：
                  ASM 库 —— ClassVisitor, MethodVisitor（字节码操作）
                  Java Compiler API —— javax.lang.model.element.ElementVisitor
                  Spring BeanDefinitionVisitor —— 遍历 BeanDefinition
                """);
    }
}
