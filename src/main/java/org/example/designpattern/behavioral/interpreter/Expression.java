package org.example.designpattern.behavioral.interpreter;

/**
 * 抽象表达式接口
 * <p>
 * 所有表达式节点都实现这个接口。
 * interpret() 方法递归地解释（计算）表达式的值。
 */
public interface Expression {

    /** 解释并返回计算结果 */
    int interpret();

    /** 返回表达式的字符串表示（方便查看语法树结构） */
    String toTreeString(String indent);
}
