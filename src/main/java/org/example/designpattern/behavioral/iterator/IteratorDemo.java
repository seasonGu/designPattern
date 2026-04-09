package org.example.designpattern.behavioral.iterator;

/**
 * 迭代器模式 —— 演示
 */
public class IteratorDemo {

    public static void main(String[] args) {

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  问题：不用迭代器，遍历不同集合要怎样？");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                必修课用数组存储：
                  Course[] courses = compulsory.getCourses();
                  for (int i = 0; i < courses.length; i++) {
                      print(courses[i]);   // 要知道是数组，用下标
                  }

                选修课用 List 存储：
                  List<Course> courses = elective.getCourses();
                  for (int i = 0; i < courses.size(); i++) {
                      print(courses.get(i));  // 要知道是 List，用 get
                  }

                问题：
                  ❌ 客户端必须知道集合的内部数据结构
                  ❌ 不同集合的遍历方式不同，无法统一处理
                  ❌ 集合内部结构改变时，所有遍历代码都要改
                """);

        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  1. 创建课程集合");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 必修课 —— 内部用数组
        CompulsoryCoursesCollection compulsory = new CompulsoryCoursesCollection(10);
        compulsory.addCourse(new Course("数据结构", "张教授", 4));
        compulsory.addCourse(new Course("操作系统", "李教授", 4));
        compulsory.addCourse(new Course("计算机网络", "王教授", 3));
        compulsory.addCourse(new Course("数据库原理", "赵教授", 3));

        // 选修课 —— 内部用 ArrayList
        ElectiveCoursesCollection elective = new ElectiveCoursesCollection();
        elective.addCourse(new Course("机器学习", "陈教授", 3));
        elective.addCourse(new Course("云计算", "刘教授", 2));
        elective.addCourse(new Course("区块链技术", "吴教授", 2));

        System.out.printf("  必修课 %d 门, 选修课 %d 门%n",
                compulsory.getCount(), elective.getCount());

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  2. 使用迭代器遍历 —— 统一方式！");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        System.out.println("  【必修课】(底层是数组，但客户端不知道也不关心)");
        printCourses(compulsory);

        System.out.println();
        System.out.println("  【选修课】(底层是 ArrayList，遍历方式完全一样!)");
        printCourses(elective);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  3. 统一处理多个集合");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        // 把不同实现的集合放在一起，统一遍历
        CourseCollection[] allCollections = {compulsory, elective};

        int totalCredits = 0;
        int totalCourses = 0;
        for (CourseCollection collection : allCollections) {
            CourseIterator it = collection.createIterator();
            while (it.hasNext()) {
                Course course = it.next();
                totalCredits += course.credits();
                totalCourses++;
            }
        }
        System.out.printf("  总共 %d 门课, 总学分: %d%n", totalCourses, totalCredits);

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  4. 迭代器可以重置，重新遍历");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println();

        CourseIterator it = compulsory.createIterator();
        System.out.println("  第一次遍历（只取前2门）:");
        int count = 0;
        while (it.hasNext() && count < 2) {
            System.out.printf("    %s%n", it.next());
            count++;
        }

        System.out.println();
        System.out.println("  重置后重新遍历（全部）:");
        it.reset();
        while (it.hasNext()) {
            System.out.printf("    %s%n", it.next());
        }

        System.out.println();
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  5. 核心理解");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("""

                迭代器模式的关键：遍历方式与集合实现解耦

                客户端代码（完全相同）：
                  CourseIterator it = collection.createIterator();
                  while (it.hasNext()) {
                      Course c = it.next();
                  }

                底层可以是：
                  ┌───────────────┐    ┌───────────────┐
                  │ 数组 [0][1][2] │    │ List → → →    │
                  │ → ArrayIterator│    │ → ListIterator │
                  └───────────────┘    └───────────────┘
                  客户端完全不知道，也不需要知道！

                Java 中无处不在的迭代器：
                  for (String s : list)     // 语法糖，编译后就是 Iterator
                  list.forEach(s -> ...)    // 内部迭代器
                  list.stream().filter()    // Stream 也是一种迭代器思想
                  ResultSet.next()          // JDBC 遍历结果集
                  Scanner.hasNext()         // 读取输入

                我们的自定义迭代器 vs Java 内置：
                  CourseIterator      ↔  java.util.Iterator<Course>
                  CourseCollection    ↔  java.lang.Iterable<Course>
                  createIterator()   ↔  iterator()
                """);
    }

    /**
     * 打印课程集合 —— 这个方法不关心集合的内部实现！
     * 不管是数组还是 List，用同样的迭代器遍历。
     */
    private static void printCourses(CourseCollection collection) {
        CourseIterator iterator = collection.createIterator();
        int index = 1;
        while (iterator.hasNext()) {
            Course course = iterator.next();
            System.out.printf("    %d. %s%n", index++, course);
        }
    }
}
