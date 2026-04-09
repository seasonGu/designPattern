package org.example.designpattern.behavioral.iterator;

/**
 * 数据类 —— 课程
 */
public record Course(String name, String teacher, int credits) {

    @Override
    public String toString() {
        return String.format("《%s》 授课: %s, %d学分", name, teacher, credits);
    }
}
