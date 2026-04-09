package org.example.designpattern.behavioral.interpreter;

/**
 * 非终结符表达式 —— 加法
 * <p>
 * 包含左右两个子表达式，interpret() 递归计算两边再相加。
 */
public class AddExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() + right.interpret();
    }

    @Override
    public String toTreeString(String indent) {
        return indent + "Add(+)\n"
                + left.toTreeString(indent + "  ├─ ") + "\n"
                + right.toTreeString(indent + "  └─ ");
    }

    @Override
    public String toString() {
        return "(" + left + " + " + right + ")";
    }
}
