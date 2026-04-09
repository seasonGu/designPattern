package org.example.designpattern.structural.flyweight;

/**
 * 享元接口 —— 棋子
 * <p>
 * draw() 方法接收外部状态（坐标 x, y）作为参数，
 * 而不是存储在对象内部。
 */
public interface ChessPiece {

    /**
     * 绘制棋子
     *
     * @param x 棋盘X坐标（外部状态）
     * @param y 棋盘Y坐标（外部状态）
     */
    void draw(int x, int y);
}
