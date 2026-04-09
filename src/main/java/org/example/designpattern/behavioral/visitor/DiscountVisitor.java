package org.example.designpattern.behavioral.visitor;

/**
 * 具体访问者 —— 折扣计算
 * <p>
 * 不同商品有不同的折扣规则：
 *   图书：9折，进口书加5%关税
 *   水果：超过5kg打85折
 *   电子产品：95折 + 延保费（保修年数 × 50元）
 */
public class DiscountVisitor implements GoodsVisitor {

    private double totalOriginal = 0;
    private double totalFinal = 0;

    @Override
    public void visit(Book book) {
        double price = book.getPrice() * 0.9;  // 9折
        if (book.isImported()) {
            price *= 1.05;  // 进口书加5%关税
            System.out.printf("    📖 %s: 原价 %.2f → 9折 + 5%%关税 = %.2f 元%n",
                    book.getName(), book.getPrice(), price);
        } else {
            System.out.printf("    📖 %s: 原价 %.2f → 9折 = %.2f 元%n",
                    book.getName(), book.getPrice(), price);
        }
        totalOriginal += book.getPrice();
        totalFinal += price;
    }

    @Override
    public void visit(Fruit fruit) {
        double price = fruit.getPrice();
        if (fruit.getWeight() > 5) {
            price *= 0.85;  // 超过5kg打85折
            System.out.printf("    🍎 %s (%.1fkg): 原价 %.2f → 超5kg享85折 = %.2f 元%n",
                    fruit.getName(), fruit.getWeight(), fruit.getPrice(), price);
        } else {
            System.out.printf("    🍎 %s (%.1fkg): 原价 %.2f → 不满5kg，原价 = %.2f 元%n",
                    fruit.getName(), fruit.getWeight(), fruit.getPrice(), price);
        }
        totalOriginal += fruit.getPrice();
        totalFinal += price;
    }

    @Override
    public void visit(Electronics electronics) {
        double price = electronics.getPrice() * 0.95;  // 95折
        double warrantyFee = electronics.getWarrantyYears() * 50.0;  // 延保费
        double finalPrice = price + warrantyFee;
        System.out.printf("    📱 %s: 原价 %.2f → 95折 %.2f + 延保%d年 %.2f = %.2f 元%n",
                electronics.getName(), electronics.getPrice(),
                price, electronics.getWarrantyYears(), warrantyFee, finalPrice);
        totalOriginal += electronics.getPrice();
        totalFinal += finalPrice;
    }

    public void printSummary() {
        System.out.printf("    💰 原价合计: %.2f 元, 折后合计: %.2f 元, 节省: %.2f 元%n",
                totalOriginal, totalFinal, totalOriginal - totalFinal);
    }
}
