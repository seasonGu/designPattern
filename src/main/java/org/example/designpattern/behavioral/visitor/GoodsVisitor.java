package org.example.designpattern.behavioral.visitor;

/**
 * 访问者接口
 * <p>
 * 为每种元素类型定义一个 visit 方法。
 * 新增操作只需新增一个 Visitor 实现类，不改任何元素类。
 * <p>
 * 注意：如果新增元素类型（如 Clothing），所有 Visitor 都要加一个 visit(Clothing)。
 * 所以访问者模式适合「元素类型稳定、操作经常变化」的场景。
 */
public interface GoodsVisitor {

    void visit(Book book);

    void visit(Fruit fruit);

    void visit(Electronics electronics);
}
