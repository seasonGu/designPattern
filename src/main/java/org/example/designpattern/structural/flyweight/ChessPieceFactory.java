package org.example.designpattern.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元工厂 —— 棋子工厂
 * <p>
 * 维护一个缓存池，确保相同颜色的棋子只创建一次。
 * 后续请求直接返回已有实例。
 * <p>
 * 这就像 Integer.valueOf() 的缓存机制。
 */
public class ChessPieceFactory {

    /** 缓存池：key=颜色, value=棋子对象 */
    private static final Map<String, ChessPiece> pool = new HashMap<>();

    /**
     * 获取棋子（有则复用，无则创建）
     */
    public static ChessPiece getChessPiece(String color) {
        ChessPiece piece = pool.get(color);
        if (piece == null) {
            piece = new ChessPieceImpl(color);
            pool.put(color, piece);
        }
        return piece;
    }

    /** 获取缓存池中的对象数量 */
    public static int getPoolSize() {
        return pool.size();
    }
}
