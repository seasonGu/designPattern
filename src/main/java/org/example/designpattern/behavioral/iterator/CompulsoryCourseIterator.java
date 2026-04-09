package org.example.designpattern.behavioral.iterator;

/**
 * 具体迭代器 —— 数组迭代器
 * <p>
 * 知道如何遍历数组结构的集合。
 * 维护一个游标（index）记录当前遍历位置。
 */
public class CompulsoryCourseIterator implements CourseIterator {

    private final Course[] courses;
    private final int count;
    private int index = 0;

    public CompulsoryCourseIterator(Course[] courses, int count) {
        this.courses = courses;
        this.count = count;
    }

    @Override
    public boolean hasNext() {
        return index < count;
    }

    @Override
    public Course next() {
        return courses[index++];
    }

    @Override
    public void reset() {
        index = 0;
    }
}
