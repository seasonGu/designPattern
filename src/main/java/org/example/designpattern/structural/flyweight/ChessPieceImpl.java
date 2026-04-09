package org.example.designpattern.structural.flyweight;

/**
 * 具体享元 —— 棋子实现
 * <p>
 * 内部状态（可共享，不变）：
 *   - color：颜色（黑/白）
 *   - shape：形状描述
 *   - texture：纹理数据（模拟大对象，实际可能是图片资源）
 *
 * 外部状态（不可共享，由客户端传入）：
 *   - x, y：棋盘坐标
 */
public class ChessPieceImpl implements ChessPiece {

    // ══════ 内部状态（共享，构造后不变）══════
    private final String color;
    private final String shape;
    private final byte[] texture;  // 模拟纹理数据占用内存

    public ChessPieceImpl(String color) {
        this.color = color;
        this.shape = "圆形棋子";
        // 模拟加载纹理资源（每个约 1MB）
        this.texture = new byte[1024 * 1024];
        System.out.printf("    [创建] 新建%s棋子对象（加载纹理 1MB）%n", color);
    }

    @Override
    public void draw(int x, int y) {
        // 外部状态 x, y 由调用方传入，不存储在对象内部
        System.out.printf("    绘制 %s%s 在 (%d, %d)%n", color, shape, x, y);
    }

    public String getColor() {
        return color;
    }
}
