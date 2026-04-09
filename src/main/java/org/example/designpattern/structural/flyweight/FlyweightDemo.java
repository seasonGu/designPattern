package org.example.designpattern.structural.flyweight;

/**
 * 享元模式 —— 演示
 */
public class FlyweightDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  问题：不用享元，每颗棋子都 new 一个对象");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""
                一盘围棋最多 361 颗棋子，每颗棋子对象包含纹理数据 1MB
                不用享元: 361 × 1MB = 361MB 内存 ❌
                用享元:   2 × 1MB   = 2MB 内存   ✅（只有黑白两个对象）
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 享元工厂 —— 相同颜色只创建一次");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        System.out.println("  下第1颗黑子:");
        ChessPiece black1 = ChessPieceFactory.getChessPiece("黑");
        black1.draw(3, 3);

        System.out.println();
        System.out.println("  下第1颗白子:");
        ChessPiece white1 = ChessPieceFactory.getChessPiece("白");
        white1.draw(3, 4);

        System.out.println();
        System.out.println("  下第2颗黑子（不会创建新对象，复用已有的）:");
        ChessPiece black2 = ChessPieceFactory.getChessPiece("黑");
        black2.draw(4, 4);

        System.out.println();
        System.out.println("  下第3颗黑子:");
        ChessPiece black3 = ChessPieceFactory.getChessPiece("黑");
        black3.draw(5, 5);

        System.out.println();
        System.out.println("  下第2颗白子:");
        ChessPiece white2 = ChessPieceFactory.getChessPiece("白");
        white2.draw(4, 5);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 验证对象共享");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        System.out.printf("  black1 == black2 ? %s%n", black1 == black2);  // true
        System.out.printf("  black1 == black3 ? %s%n", black1 == black3);  // true
        System.out.printf("  white1 == white2 ? %s%n", white1 == white2);  // true
        System.out.printf("  black1 == white1 ? %s%n", black1 == white1);  // false
        System.out.println();
        System.out.printf("  下了5颗棋子，实际只创建了 %d 个对象 ✅%n",
                ChessPieceFactory.getPoolSize());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 大规模场景 —— 模拟整盘棋");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        int totalPieces = 0;
        // 模拟下100手棋
        for (int i = 0; i < 100; i++) {
            String color = (i % 2 == 0) ? "黑" : "白";
            ChessPiece piece = ChessPieceFactory.getChessPiece(color);
            // piece.draw(...)  // 省略打印
            totalPieces++;
        }

        System.out.printf("  下了 %d 颗棋子%n", totalPieces);
        System.out.printf("  缓存池中只有 %d 个对象%n", ChessPieceFactory.getPoolSize());
        System.out.printf("  不用享元内存: %d × 1MB = %dMB%n", totalPieces, totalPieces);
        System.out.printf("  使用享元内存: %d × 1MB = %dMB%n",
                ChessPieceFactory.getPoolSize(), ChessPieceFactory.getPoolSize());
        System.out.printf("  节省内存: %dMB ✅%n", totalPieces - ChessPieceFactory.getPoolSize());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                享元模式的关键：分离内部状态和外部状态

                ┌────────────────────────────┐
                │    ChessPieceImpl (享元)    │
                │                            │
                │  内部状态（共享，不变）：      │
                │    color = "黑"             │  ← 存在对象里，所有黑子共享
                │    shape = "圆形棋子"       │
                │    texture = [1MB数据]      │
                │                            │
                │  外部状态（不存储）：         │
                │    draw(x, y)              │  ← 由调用方传入，每次不同
                └────────────────────────────┘

                Java 中的享元模式：
                  Integer.valueOf(127) == Integer.valueOf(127)  → true  (缓存池)
                  Integer.valueOf(128) == Integer.valueOf(128)  → false (超出缓存范围)
                  String s1 = "abc"; String s2 = "abc"; s1 == s2 → true (字符串常量池)
                """);
    }
}
