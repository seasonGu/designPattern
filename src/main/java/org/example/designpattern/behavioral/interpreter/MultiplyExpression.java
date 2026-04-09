package org.example.designpattern.behavioral.interpreter;

/**
 * 非终结符表达式 —— 乘法
 */
public class MultiplyExpression implements Expression {

    private final Expression left;
    private final Expression right;

    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int interpret() {
        return left.interpret() * right.interpret();
    }

    @Override
    public String toTreeString(String indent) {
        return indent + "Mul(*)\n"
                + left.toTreeString(indent + "  ├─ ") + "\n"
                + right.toTreeString(indent + "  └─ ");
    }

    @Override
    public String toString() {
        return "(" + left + " * " + right + ")";
    }
}
