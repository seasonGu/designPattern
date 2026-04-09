package org.example.designpattern.behavioral.iterator;

/**
 * 聚合接口 —— 课程集合
 * <p>
 * 定义创建迭代器的工厂方法。
 * 不同的集合实现返回不同的迭代器，但接口统一。
 */
public interface CourseCollection {

    /** 创建迭代器 —— 工厂方法 */
    CourseIterator createIterator();

    /** 添加课程 */
    void addCourse(Course course);

    /** 获取课程数量 */
    int getCount();

    /** 获取集合名称 */
    String getName();
}
