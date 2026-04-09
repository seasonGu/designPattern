package org.example.designpattern.behavioral.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * 表达式解析器
 * <p>
 * 将字符串表达式解析成 Expression 语法树。
 * 支持 +、-、* 运算符，正确处理运算优先级（* 优先于 +/-）。
 * <p>
 * 使用递归下降解析：
 *   expression = term (('+' | '-') term)*
 *   term       = factor ('*' factor)*
 *   factor     = NUMBER
 * <p>
 * 这里为了简单，只处理正整数和空格分隔的表达式，如 "3 + 5 * 2 - 1"。
 */
public class ExpressionParser {

    private List<String> tokens;
    private int pos;

    /**
     * 解析表达式字符串，返回语法树根节点
     */
    public Expression parse(String expression) {
        this.tokens = tokenize(expression);
        this.pos = 0;
        return parseExpression();
    }

    /**
     * 词法分析 —— 将字符串拆分成 token 列表
     * "3 + 5 * 2 - 1" → ["3", "+", "5", "*", "2", "-", "1"]
     */
    private List<String> tokenize(String expression) {
        List<String> result = new ArrayList<>();
        StringBuilder number = new StringBuilder();

        for (char c : expression.toCharArray()) {
            if (Character.isDigit(c)) {
                number.append(c);
            } else if (c == '+' || c == '-' || c == '*') {
                if (!number.isEmpty()) {
                    result.add(number.toString());
                    number.setLength(0);
                }
                result.add(String.valueOf(c));
            } else if (c == ' ') {
                if (!number.isEmpty()) {
                    result.add(number.toString());
                    number.setLength(0);
                }
            }
        }
        if (!number.isEmpty()) {
            result.add(number.toString());
        }
        return result;
    }

    /**
     * 解析 expression = term (('+' | '-') term)*
     * 处理加法和减法（低优先级）
     */
    private Expression parseExpression() {
        Expression left = parseTerm();

        while (pos < tokens.size()) {
            String op = tokens.get(pos);
            if ("+".equals(op)) {
                pos++;
                Expression right = parseTerm();
                left = new AddExpression(left, right);
            } else if ("-".equals(op)) {
                pos++;
                Expression right = parseTerm();
                left = new SubtractExpression(left, right);
            } else {
                break;
            }
        }
        return left;
    }

    /**
     * 解析 term = factor ('*' factor)*
     * 处理乘法（高优先级）
     */
    private Expression parseTerm() {
        Expression left = parseFactor();

        while (pos < tokens.size()) {
            String op = tokens.get(pos);
            if ("*".equals(op)) {
                pos++;
                Expression right = parseFactor();
                left = new MultiplyExpression(left, right);
            } else {
                break;
            }
        }
        return left;
    }

    /**
     * 解析 factor = NUMBER
     * 终结符 —— 数字
     */
    private Expression parseFactor() {
        String token = tokens.get(pos++);
        return new NumberExpression(Integer.parseInt(token));
    }
}
