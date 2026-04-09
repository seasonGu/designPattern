package org.example.designpattern.behavioral.interpreter;

/**
 * 解释器模式 —— 演示
 */
public class InterpreterDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  问题：如何计算用户输入的数学表达式？");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                硬编码方式：
                  // 每种表达式写一个 if-else
                  if (expression.contains("+")) {
                      String[] parts = expression.split("\\\\+");
                      return Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]);
                  }
                  // 两个运算符的组合呢？三个呢？优先级呢？
                  // 根本无法扩展！

                解释器模式：
                  把表达式解析成语法树，每个节点递归解释自己的部分
                  新增运算符 = 新增一个 Expression 类
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 手动构建语法树");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 表达式: 3 + 5
        Expression simple = new AddExpression(
                new NumberExpression(3),
                new NumberExpression(5)
        );
        System.out.printf("  3 + 5 = %d%n", simple.interpret());
        System.out.println();

        // 表达式: 3 + 5 * 2（乘法优先）
        // 语法树:    Add
        //           /   \
        //         3     Mul
        //              /   \
        //             5     2
        Expression withPriority = new AddExpression(
                new NumberExpression(3),
                new MultiplyExpression(
                        new NumberExpression(5),
                        new NumberExpression(2)
                )
        );
        System.out.println("  手动构建: 3 + 5 * 2");
        System.out.println("  语法树:");
        System.out.println(withPriority.toTreeString("    "));
        System.out.printf("  计算结果: %d%n", withPriority.interpret());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 使用解析器自动构建语法树");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        ExpressionParser parser = new ExpressionParser();

        String[] expressions = {
                "3 + 5",
                "10 - 3",
                "4 * 6",
                "3 + 5 * 2",
                "3 + 5 * 2 - 1",
                "10 * 2 + 3 * 4",
                "100 - 20 * 3 + 5",
        };

        for (String expr : expressions) {
            Expression tree = parser.parse(expr);
            System.out.printf("  %-20s = %d%n", expr, tree.interpret());
        }

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 查看语法树结构");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        String complexExpr = "3 + 5 * 2 - 1";
        Expression complexTree = parser.parse(complexExpr);
        System.out.println("  表达式: " + complexExpr);
        System.out.println("  解析后: " + complexTree);
        System.out.println("  语法树:");
        System.out.println(complexTree.toTreeString("    "));
        System.out.printf("  结果: %d%n", complexTree.interpret());

        System.out.println();
        System.out.println("  表达式: 10 * 2 + 3 * 4");
        Expression tree2 = parser.parse("10 * 2 + 3 * 4");
        System.out.println("  语法树:");
        System.out.println(tree2.toTreeString("    "));
        System.out.printf("  结果: %d%n", tree2.interpret());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                解释器模式的关键：语法规则 → 类层次结构

                文法规则：
                  expression = term (('+' | '-') term)*
                  term       = factor ('*' factor)*
                  factor     = NUMBER

                对应的类：
                  Expression (接口)
                    ├── NumberExpression    (终结符 = 叶子节点)
                    ├── AddExpression       (非终结符 = 分支节点)
                    ├── SubtractExpression
                    └── MultiplyExpression

                解释过程（递归）：
                  "3 + 5 * 2" 的语法树：
                       Add.interpret()         → 3 + 10 = 13
                      /              \\
                  Num(3).interpret()  Mul.interpret()   → 5 * 2 = 10
                  → 返回 3            /          \\
                              Num(5).interpret()  Num(2).interpret()
                              → 返回 5            → 返回 2

                Java/Spring 中的解释器模式：
                  Spring SpEL  —— #{user.name} 表达式求值
                  正则表达式    —— Pattern.compile("a*b") 就是解释器
                  SQL 解析器    —— MyBatis 的动态 SQL
                  EL 表达式     —— JSP ${user.name}
                  规则引擎      —— Drools
                """);
    }
}
