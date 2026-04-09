package org.example.designpattern.behavioral.iterator;

/**
 * 迭代器接口
 * <p>
 * 定义遍历集合的统一方式，不管集合内部用什么数据结构。
 * 类似 java.util.Iterator，这里自定义一个以展示原理。
 */
public interface CourseIterator {

    /** 是否还有下一个元素 */
    boolean hasNext();

    /** 获取下一个元素 */
    Course next();

    /** 重置迭代器到起始位置 */
    void reset();
}
