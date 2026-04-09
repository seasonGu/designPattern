package org.example.designpattern.behavioral.iterator;

import java.util.List;

/**
 * 具体迭代器 —— List 迭代器
 * <p>
 * 知道如何遍历 List 结构的集合。
 */
public class ElectiveCourseIterator implements CourseIterator {

    private final List<Course> courses;
    private int index = 0;

    public ElectiveCourseIterator(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean hasNext() {
        return index < courses.size();
    }

    @Override
    public Course next() {
        return courses.get(index++);
    }

    @Override
    public void reset() {
        index = 0;
    }
}
