package org.example.designpattern.behavioral.iterator;

/**
 * 具体聚合 —— 必修课集合（数组实现）
 * <p>
 * 内部使用固定大小的数组存储课程。
 * 客户端不需要知道这是数组，只通过迭代器访问。
 */
public class CompulsoryCoursesCollection implements CourseCollection {

    /** 内部用数组存储 —— 客户端不可见 */
    private final Course[] courses;
    private int count = 0;

    public CompulsoryCoursesCollection(int capacity) {
        this.courses = new Course[capacity];
    }

    @Override
    public CourseIterator createIterator() {
        // 返回针对数组的迭代器
        return new CompulsoryCourseIterator(courses, count);
    }

    @Override
    public void addCourse(Course course) {
        if (count >= courses.length) {
            System.out.println("    ⚠️ 必修课已满，无法添加: " + course.name());
            return;
        }
        courses[count++] = course;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public String getName() {
        return "必修课";
    }
}
