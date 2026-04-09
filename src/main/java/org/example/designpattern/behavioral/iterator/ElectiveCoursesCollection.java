package org.example.designpattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚合 —— 选修课集合（List实现）
 * <p>
 * 内部使用 ArrayList 存储课程。
 * 和必修课的数组实现完全不同，但客户端用同样的迭代器接口遍历。
 */
public class ElectiveCoursesCollection implements CourseCollection {

    /** 内部用 List 存储 —— 客户端不可见 */
    private final List<Course> courses = new ArrayList<>();

    @Override
    public CourseIterator createIterator() {
        // 返回针对 List 的迭代器
        return new ElectiveCourseIterator(courses);
    }

    @Override
    public void addCourse(Course course) {
        courses.add(course);
    }

    @Override
    public int getCount() {
        return courses.size();
    }

    @Override
    public String getName() {
        return "选修课";
    }
}
