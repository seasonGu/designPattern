package org.example.designpattern.behavioral.interpreter;

/**
 * 终结符表达式 —— 数字
 * <p>
 * 语法树的叶子节点。直接返回数值，不再递归。
 */
public class NumberExpression implements Expression {

    private final int value;

    public NumberExpression(int value) {
        this.value = value;
    }

    @Override
    public int interpret() {
        return value;
    }

    @Override
    public String toTreeString(String indent) {
        return indent + value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
